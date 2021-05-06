package com.jslee.retrofittesting.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quiz,container,false)

        Log.d("jjslee", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)


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