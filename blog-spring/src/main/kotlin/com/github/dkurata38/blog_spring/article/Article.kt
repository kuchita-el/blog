package com.github.dkurata38.blog_spring.article

import java.time.Instant

class Article(val id: String, val headline: String, val body: String, val postedAt: Instant, val version: Int = 0) {
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
}