package com.demo.quiz.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.quiz.databinding.ActivityMainBinding
import com.demo.quiz.viewmodel.QuestionViewModel

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        startQuizFragment()
    }

    private fun startQuizFragment() {
        supportFragmentManager.beginTransaction().apply {
            val quizFragment = QuizFragment()
            add(activityMainBinding.container.id, quizFragment)
            addToBackStack(null)
            commit()
        }
    }
}