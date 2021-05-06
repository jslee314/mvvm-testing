package com.jslee.retrofittesting.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentQuizBinding>(inflater,
            R.layout.fragment_quiz,container,false)


        // quiz button에 navigaion.xml에서 만든 액션 추가
//        binding.quizBtn.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_quizFragment_to_answerFragment)
//        }
        binding.quizBtn.setOnClickListener {view : View ->

            if(binding.checkBox1.isChecked && binding.checkBox2.isChecked){
                view.findNavController().navigate(R.id.action_quizFragment_to_successFragment)

            }
            else{
                view.findNavController().navigate(R.id.action_quizFragment_to_failFragment)

            }

        }

        return binding.root
    }


}