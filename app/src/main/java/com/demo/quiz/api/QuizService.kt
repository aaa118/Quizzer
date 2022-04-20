package com.demo.quiz.api

import com.demo.quiz.model.QuizResponse
import retrofit2.Call
import retrofit2.http.GET

interface QuizService {
    @GET("api.php?amount=10&category=9&difficulty=medium&type=multiple")
   suspend fun getResponse(): QuizResponse?
}