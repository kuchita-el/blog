package com.github.dkurata38.blog_spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogSpringApplication

fun main(args: Array<String>) {
    runApplication<BlogSpringApplication>(*args)
}
