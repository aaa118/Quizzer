package com.demo.quiz.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.quiz.databinding.ActivityMainBinding
import com.demo.quiz.viewmodel.QuestionViewModel


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var questionViewModel: QuestionViewModel

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val quizFragment = QuizFragment()

        supportFragmentManager.beginTransaction().apply {
            add(activityMainBinding.container.id, quizFragment)
            addToBackStack(null)
            commit()
        }
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)


        questionViewModel.getListOfResultsLiveData().observe(this, Observer {
            Log.i(TAG, "onCreate: $it")
        })

//        activityMainBinding.buttonLoad.setOnClickListener {
//            questionViewModel.startLoading()
//        }
    }
}