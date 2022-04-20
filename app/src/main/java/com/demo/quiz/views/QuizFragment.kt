package com.demo.quiz.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.quiz.R
import com.demo.quiz.databinding.FragmentQuizBinding
import com.demo.quiz.model.Results
import com.demo.quiz.viewmodel.QuestionViewModel
import com.demo.quiz.viewmodel.ViewModelFactory
import java.util.ArrayList

private const val TAG = "QuizFragment"

class QuizFragment : Fragment() {

    lateinit var fragmentQuizBinding: FragmentQuizBinding
    private lateinit var questionViewModel: QuestionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater,container, false)
        questionViewModel = ViewModelProvider(this, ViewModelFactory(context)).get(QuestionViewModel::class.java)

        return fragmentQuizBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentQuizBinding.buttonLoad.setOnClickListener {
            Log.i(TAG, "onViewCreated: Start Quiz")
            questionViewModel.startLoading()
        }

        questionViewModel.getListOfResultsLiveData().observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "onViewCreated: $it")
            startQuestionsFragment(it)
        })
    }

    private fun startQuestionsFragment(list: List<Results?>) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            val questionFragment = QuestionFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("res", list as ArrayList<Results?>)
                }
            }
            replace(R.id.container, questionFragment)
            addToBackStack(null)
            commit()
        }
    }

}