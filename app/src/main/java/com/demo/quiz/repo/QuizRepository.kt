package com.demo.quiz.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.demo.quiz.api.RetrofitInstance
import com.demo.quiz.db.AppDatabase
import com.demo.quiz.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "QuizRepository"

class QuizRepository(private val context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()

    suspend fun makeSyncApiCall(): List<Results?>? {
        val retrofitInstance = RetrofitInstance()
        val response = retrofitInstance.setupRetrofitInstance()?.getResponse()
        if (response != null) {
            Log.i(TAG, "makeSyncApiCall: $response")
            db.resultsDao().insertAll(response.listResults)
            return response.listResults
        }
        return null
    }
}