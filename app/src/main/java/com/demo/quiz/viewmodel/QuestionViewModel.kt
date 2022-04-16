package com.demo.quiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.quiz.model.Results
import com.demo.quiz.repo.QuizRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {

    private val listOfResultsLiveData: MutableLiveData<List<Results?>> = MutableLiveData()

    fun getListOfResultsLiveData(): MutableLiveData<List<Results?>> {
        return listOfResultsLiveData
    }

    @DelicateCoroutinesApi
    fun startLoading() {
        GlobalScope.launch(Dispatchers.IO) {
            val quizRepository = QuizRepository()
            val results = quizRepository.makeSyncApiCall()
            if (results != null) {
                listOfResultsLiveData.postValue(results)
            }
        }


    }

}