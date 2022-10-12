package com.marcos012.movies.handlers

import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import java.util.*
import java.util.stream.Collectors

class ApiError
private constructor(val statusCode: Int, val error: String, val messages: List<String>) {

    private constructor(statusCode: Int, error: String, message: String?) : this(
        statusCode,
        error,
        if (Objects.nonNull(message)) listOf<String>(message!!)
        else emptyList<String>()
    )

    companion object {
        @JvmOverloads
        fun fromHttpError(
            httpStatus: HttpStatus,
            exception: Exception,
            errorIdentifier: String = httpStatus.reasonPhrase
        ): ApiError {
            return ApiError(httpStatus.value(), errorIdentifier, exception.message)
        }

        fun fromMessage(httpStatus: HttpStatus, message: String?): ApiError {
            return ApiError(httpStatus.value(), httpStatus.reasonPhrase, message)
        }

        fun fromBindingResult(bindingResult: BindingResult): ApiError {
            val erros = bindingResult
                .allErrors
                .stream()
                .map { error ->
                    val fieldError = error as FieldError
                    fieldError.field + " : " + fieldError.defaultMessage
                }
                .collect(Collectors.toList())

            return ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase, erros
            )

        }
    }
}
