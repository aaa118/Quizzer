package com.demo.quiz.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("response_code")
    val response: String,
    @SerializedName("results")
    val listResults: List<Results>
)