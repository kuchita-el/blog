package com.github.dkurata38.blog_spring.article

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.time.Instant
import java.util.*

@Controller
@SessionAttributes(PostArticleController.FORM_MODEL_NAME)
class PostArticleController(private val articleRepository: ArticleRepository) {
    companion object {
        const val FORM_MODEL_NAME = "postArticleForm"
        const val ERROR_ATTRIBUTE_NAME: String = "errors"
    }

    @ModelAttribute(FORM_MODEL_NAME)
    fun postArticleForm(): PostArticleForm {
        return PostArticleForm()
    }

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.initDirectFieldAccess()
    }

    @GetMapping("article/new")
    fun viewForm(@ModelAttribute(FORM_MODEL_NAME) postArticleForm: PostArticleForm, @RequestParam(name = "back", defaultValue = "false") back: Boolean, modelMap: ModelMap): ModelAndView {
        val view = ModelAndView("article/new")
        view.addObject(FORM_MODEL_NAME, if (back) postArticleForm() else postArticleForm)
        val maybeBindingResult = modelMap[ERROR_ATTRIBUTE_NAME]
        if (maybeBindingResult != null) {
            view.addAllObjects((maybeBindingResult as BindingResult).model)
        }
        return view
    }

    @PostMapping("article/post")
    fun post(
        @ModelAttribute(FORM_MODEL_NAME) @Validated postArticleForm: PostArticleForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
        sessionStatus: SessionStatus
    ): RedirectView {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(ERROR_ATTRIBUTE_NAME, bindingResult)
            return RedirectView("new")
        }

        val article = Article(UUID.randomUUID().toString(), postArticleForm.headline, postArticleForm.body, Instant.now())
        articleRepository.save(article)
        sessionStatus.setComplete()
        return RedirectView("/")
    }
}

data class PostArticleForm(@get:NotEmpty(message = "見出しを入力してください。") @get:Size(max = 100, message = "見出しは100文字まで入力できます。") val headline: String = "", @get:NotEmpty(message = "本文を入力してください。") val body: String = "")