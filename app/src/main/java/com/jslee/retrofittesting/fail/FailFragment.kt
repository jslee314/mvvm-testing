package com.jslee.retrofittesting.fail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentFailBinding

class FailFragment : Fragment() {
    private lateinit var binding: FragmentFailBinding
    private lateinit var viewModel: FailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_fail,container,false)

        viewModel = ViewModelProvider(this).get(FailViewModel::class.java)

        // button에 navigaion.xml에서 만든 액션 추가
        binding.failBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_failFragment_to_quizFragment)
        }

        return binding.root
    }

}