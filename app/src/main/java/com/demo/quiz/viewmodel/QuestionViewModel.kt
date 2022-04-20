package com.demo.quiz.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.quiz.model.Results
import com.demo.quiz.repo.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val TAG = "QuestionViewModel"

class QuestionViewModel(private val context: Context?) : ViewModel() {
    private val listOfResultsLiveData: MutableLiveData<List<Results?>> = MutableLiveData()

    fun getListOfResultsLiveData(): MutableLiveData<List<Results?>> {
        return listOfResultsLiveData
    }

    fun startLoading() {
        runBlocking {
            launch(Dispatchers.IO) {
                if (context != null) {
                val quizRepository = QuizRepository(context)
                    val results = quizRepository.makeSyncApiCall()
                    if (results != null) {
                        listOfResultsLiveData.postValue(results)
                    }
                } else {
                    Log.i(TAG, "startLoading: Context is null")
                }
            }
        }
    }
}