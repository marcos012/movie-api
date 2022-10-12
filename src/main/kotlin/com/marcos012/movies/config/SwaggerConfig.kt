package com.marcos012.movies.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    private val apiInfo: ApiInfo
        get() = ApiInfoBuilder()
            .title("Movies API")
            .description("API of movies")
            .version("1.0.0")
            .contact(
                Contact("Marcos012", "https://github.com/marcos012", null)
            )
            .build()

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
    }

    companion object {
        private const val BASE_PACKAGE = "com.marcos012.movies"
    }
}