package com.jslee.retrofittesting.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentAnswerBinding


class AnswerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentAnswerBinding>(inflater,
            R.layout.fragment_answer,container,false)
        return binding.root
    }

}