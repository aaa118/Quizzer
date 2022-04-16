package com.demo.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.quiz.api.RetrofitInstance
import com.demo.quiz.databinding.ActivityMainBinding
import com.demo.quiz.model.QuizResponse
import com.demo.quiz.model.Results
import com.demo.quiz.viewmodel.QuestionViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var questionViewModel: QuestionViewModel

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)


        questionViewModel.getListOfResultsLiveData().observe(this, Observer {
            Log.i(TAG, "onCreate: $it")
        })

        activityMainBinding.buttonLoad.setOnClickListener {
            questionViewModel.startLoading()
        }


    }
}