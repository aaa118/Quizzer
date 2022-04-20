package com.demo.quiz.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            QuestionViewModel(this.context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
