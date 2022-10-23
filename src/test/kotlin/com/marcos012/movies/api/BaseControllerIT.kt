package com.marcos012.movies.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.marcos012.movies.MoviesApiApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import java.io.IOException

@SpringBootTest(
    classes = [MoviesApiApplication::class, FlywayAutoConfiguration.FlywayConfiguration::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class BaseControllerIT {
    @Autowired
    lateinit var mvc: MockMvc

    @Throws(IOException::class)
    fun toJson(data: Any): String {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return mapper.writeValueAsString(data)
    }
}