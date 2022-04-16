package com.demo.quiz.repo

import android.util.Log
import com.demo.quiz.api.RetrofitInstance
import com.demo.quiz.model.QuizResponse
import com.demo.quiz.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizRepository {
    private val TAG = "QuizRepository"

     fun makeSyncApiCall(): List<Results?>? {
        val retrofitInstance = RetrofitInstance()
        val response = retrofitInstance.setupRetrofitInstance()?.getResponse()?.execute()
        if (response != null) {
            if (response.isSuccessful) {
                return response.body()?.listResults
            }
        }
        return null
    }

    suspend fun makeApiCall() : List<Results?>? {
        val retrofitInstance = RetrofitInstance()
        var listOfResults: List<Results?>? = null
        retrofitInstance.setupRetrofitInstance()?.getResponse()?.enqueue(object :
            Callback<QuizResponse?> {
            override fun onResponse(
                call: Call<QuizResponse?>,
                response: Response<QuizResponse?>
            )  {
                if (response.isSuccessful && response.body() != null) {
                    listOfResults = response.body()!!.listResults

                }
            }

            override fun onFailure(call: Call<QuizResponse?>, t: Throwable) {
                Log.i(TAG, "onResponse: ${t.localizedMessage}")
            }

        })
        return listOfResults
    }
}