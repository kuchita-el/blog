package com.github.dkurata38.blog_spring.article

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.time.Instant

class Article(@Id val id: String, val headline: String, val body: String, val postedAt: Instant, @Version val version: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun withVersion(version: Int): Article {
        return Article(id, headline, body, postedAt, version)
    }
}