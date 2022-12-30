package com.github.dkurata38.blog_spring.article

import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.time.Instant
import java.util.UUID

@Controller
class PostArticleController(private val articleRepository: ArticleRepository) {
    companion object {
        const val ERROR_ATTRIBUTE_NAME: String = "errors"
    }

    @GetMapping("article/new")
    fun viewForm(@ModelAttribute postArticleForm: PostArticleForm): ModelAndView {
        return ModelAndView("article/new")
            .addObject(postArticleForm)
    }

    @PostMapping("article/post")
    fun post(
        @ModelAttribute @Validated postArticleForm: PostArticleForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): RedirectView {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(ERROR_ATTRIBUTE_NAME, bindingResult)
            return RedirectView("new")
        }

        val article = Article(UUID.randomUUID().toString(), postArticleForm.headline!!, postArticleForm.body!!, Instant.now())
        articleRepository.save(article)
        return RedirectView("new")
    }
}

data class PostArticleForm(val headline: String?, val body: String?)