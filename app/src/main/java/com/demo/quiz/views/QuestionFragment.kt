package com.demo.quiz.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.demo.quiz.databinding.FragmentQuestionBinding
import com.demo.quiz.model.Results
import android.widget.RadioButton




class QuestionFragment : Fragment() {

    lateinit var fragmentQuestionBinding: FragmentQuestionBinding

    var list: List<Results?>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentQuestionBinding = FragmentQuestionBinding.inflate(inflater, container, false)
        return fragmentQuestionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            list = it.getParcelableArrayList("res")
        }
        if (list != null) {
            val _list = list
            if (_list != null) {
                var i = 0
                setupQuestions(_list, i)
                fragmentQuestionBinding.nextButton.setOnClickListener {
                    while (i < _list.size - 1) {
                        i++
                        setupQuestions(_list, i)
//                        fragmentQuestionBinding.questionTextView.text = _list[i]?.question ?: ""
                        break
                    }
                }
            }
        }
    }

    private fun setupQuestions(
        _list: List<Results?>,
        i: Int
    ) {
        fragmentQuestionBinding.answersRadioGroup.removeAllViews()
        fragmentQuestionBinding.questionTextView.text = _list[i]?.question ?: ""
        fragmentQuestionBinding.answersRadioGroup.orientation = LinearLayout.VERTICAL
        val incorrectAnswersList = _list[i]?.incorrectAnswers
        if (!incorrectAnswersList.isNullOrEmpty()) {
//            var j = 0
            for (j in 0 until (incorrectAnswersList.size)) {
                val radioButton = RadioButton(context)
                radioButton.id = j
                radioButton.text = incorrectAnswersList[j]
                fragmentQuestionBinding.answersRadioGroup.addView(radioButton)
            }
            val radioButton = RadioButton(context)
            radioButton.id = _list.size
            radioButton.text = _list[i]?.correctAnswer
            fragmentQuestionBinding.answersRadioGroup.addView(radioButton)
        }

    }

}
