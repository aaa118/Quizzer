package com.demo.quiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.quiz.model.Results
import com.demo.quiz.repo.QuizRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class QuestionViewModel : ViewModel() {
    private val listOfResultsLiveData: MutableLiveData<List<Results?>> = MutableLiveData()

    fun getListOfResultsLiveData(): MutableLiveData<List<Results?>> {
        return listOfResultsLiveData
    }

    fun startLoading() {
        runBlocking {
            launch(Dispatchers.IO) {
                val quizRepository = QuizRepository()
                val results = quizRepository.makeSyncApiCall()
                if (results != null) {
                    listOfResultsLiveData.postValue(results)
                }
            }
        }
    }
}