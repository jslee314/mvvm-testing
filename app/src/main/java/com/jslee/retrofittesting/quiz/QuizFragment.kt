package com.jslee.retrofittesting.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentQuizBinding>(inflater,
            R.layout.fragment_quiz,container,false)
        return binding.root
    }


}