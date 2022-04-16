package com.demo.quiz.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private var BASE_URL = "https://opentdb.com/"

    fun setupRetrofitInstance(): QuizService? {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(QuizService::class.java)

    }
}