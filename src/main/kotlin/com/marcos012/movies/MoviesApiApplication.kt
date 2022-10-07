package com.marcos012.movies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableCaching
@EnableFeignClients
@SpringBootApplication
class MoviesApiApplication

fun main(args: Array<String>) {
	runApplication<MoviesApiApplication>(*args)
}
