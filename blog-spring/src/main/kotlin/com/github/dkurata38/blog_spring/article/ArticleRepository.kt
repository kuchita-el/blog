package com.github.dkurata38.blog_spring.article

import org.springframework.data.repository.PagingAndSortingRepository

interface ArticleRepository: PagingAndSortingRepository<Article, String> {
}