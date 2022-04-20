package com.demo.quiz.repo

import android.util.Log
import com.demo.quiz.api.RetrofitInstance
import com.demo.quiz.model.QuizResponse
import com.demo.quiz.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "QuizRepository"

class QuizRepository {

    suspend fun makeSyncApiCall(): List<Results?>? {
        val retrofitInstance = RetrofitInstance()
        val response = retrofitInstance.setupRetrofitInstance()?.getResponse()
        if (response != null) {
            Log.i(TAG, "makeSyncApiCall: $response")
            return response.listResults
        }
        return null
    }
}