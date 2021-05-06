package com.jslee.retrofittesting.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentSuccessBinding>(inflater,
            R.layout.fragment_success,container,false)

        // button에 navigaion.xml에서 만든 액션 추가
        binding.successBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_successFragment_to_quizFragment)
        }

        return binding.root
    }

}