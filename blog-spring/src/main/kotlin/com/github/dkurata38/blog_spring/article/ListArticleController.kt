package com.github.dkurata38.blog_spring.article

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ListArticleController(private val articleRepository: ArticleRepository) {
    @GetMapping("/")
    fun list(model: Model): ModelAndView {
        val articles = articleRepository.findAll(Sort.by("postedAt").descending())
        return ModelAndView("article/list")
            .addObject("articles", articles)
    }
}