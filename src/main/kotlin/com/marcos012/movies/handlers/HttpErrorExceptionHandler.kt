package com.marcos012.movies.handlers

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.marcos012.movies.handlers.validation.DomainBusinessException
import com.marcos012.movies.handlers.validation.ForbiddenException
import feign.FeignException
import feign.codec.DecodeException
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.HandlerMapping
import javax.persistence.EntityNotFoundException


@ControllerAdvice
class HttpErrorExceptionHandler {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val unexpectedErrorMessage = "Unexpected error."

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DomainBusinessException::class)
    @ResponseBody
    fun businessException(e: DomainBusinessException): ApiError {
        logger.error("Business error: " + e.message, e)
        return ApiError.fromHttpError(UNPROCESSABLE_ENTITY, e)
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    @ResponseBody
    fun notFoundException(e: RuntimeException): ApiError {
        logger.error("Not found: " + e.message, e)
        return ApiError.fromHttpError(NOT_FOUND, e)
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun serverException(e: Exception): ApiError {
        logger.error(unexpectedErrorMessage + e.message, e)
        return ApiError.fromMessage(INTERNAL_SERVER_ERROR, unexpectedErrorMessage)
    }

    @ExceptionHandler(FeignException::class)
    @ResponseBody
    fun feignException(e: FeignException): ResponseEntity<*> {
        logger.error("FEIGN ERROR: " + e.message, e)
        return ResponseEntity.status(e.status()).build<Any>()
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(AccessDeniedException::class)
    @ResponseBody
    fun accessDeniedException(e: AccessDeniedException): ApiError {
        logger.error("Access Denied: " + e.message, e)
        return ApiError.fromMessage(FORBIDDEN, e.message)
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidOperationException::class)
    @ResponseBody
    fun invalidOperationException(e: InvalidOperationException): ApiError {
        logger.error("INVALID OPERATION: " + e.message, e)
        return ApiError.fromMessage(BAD_REQUEST, e.message)
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(ForbiddenException::class)
    @ResponseBody
    fun forbiddenException(e: ForbiddenException): ApiError {
        logger.error("FORBIDDEN " + e.message, e)
        return ApiError.fromMessage(FORBIDDEN, e.message)
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(
        MethodArgumentTypeMismatchException::class,
        MethodArgumentNotValidException::class
    )
    @ResponseBody
    fun invalidParamException(
        e: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ResponseEntity<*> {
        logger.error("Invalid param - name: ${e.name} - value: ${e.value}", e)
        val isEnum = e.requiredType!!.isEnum
        val parameter = e.name

        return if (isEnum && pathVariableInvalidParam(request, parameter))
            ResponseEntity.notFound().build<Any>()
        else
            ResponseEntity.badRequest().body(ApiError.fromMessage(BAD_REQUEST, e.message))
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(
        e: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<*> {
        return when (val cause = e.cause) {
            is MissingKotlinParameterException -> handleMissingKotlinParameterException(cause)
            is JsonMappingException -> handleJsonMappingException(cause)
            else -> {
                logger.error("INVALID PARAM", e)
                ResponseEntity.badRequest().body(ApiError.fromMessage(BAD_REQUEST, e.message))
            }
        }
    }

    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(BadRequest::class)
    fun badRequestException(exception: BadRequest) = ApiError.fromMessage(FORBIDDEN, exception.message)


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(DecodeException::class)
    fun decodeException(exception: BadRequest) = ApiError.fromMessage(INTERNAL_SERVER_ERROR, exception.message)

    private fun handleJsonMappingException(error: JsonMappingException): ResponseEntity<ApiError> {
        val maxLengthError = "Number max length"
        val field = error.path.last().fieldName
        val message = StringBuilder()

        error.path.forEach {
            val fieldName = it.fieldName
            message.append("$fieldName: required")
        }

        logger.error("Bad request: $message", error)

        return when {
            error.message?.contains("out of range of int")!! -> ResponseEntity.badRequest()
                .body(ApiError.fromMessage(BAD_REQUEST, "$field: $maxLengthError"))
            else -> ResponseEntity.badRequest().body(ApiError.fromMessage(BAD_REQUEST, error.message))
        }
    }

    private fun handleMissingKotlinParameterException(error: MissingKotlinParameterException): ResponseEntity<ApiError> {
        val message = StringBuilder()

        error.path.forEach {
            val field = it.fieldName
            message.append("$field: required")
        }

        logger.error(message.toString(), error)

        return ResponseEntity.badRequest().body(ApiError.fromMessage(BAD_REQUEST, message.toString()))
    }

    private fun pathVariableInvalidParam(request: WebRequest, param: String): Boolean {
        val variables = (request as ServletWebRequest)
            .request
            .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as Map<*, *>

        return variables.containsKey(param)
    }
}
