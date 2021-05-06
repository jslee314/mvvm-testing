package com.jslee.retrofittesting.success

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
import com.jslee.retrofittesting.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    private lateinit var binding : FragmentSuccessBinding
    private lateinit var viewModel: SuccessViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_success,container,false)

        Log.d("jjslee", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(SuccessViewModel::class.java)

        // button에 navigaion.xml에서 만든 액션 추가
        binding.successBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_successFragment_to_quizFragment)
        }

        return binding.root
    }

}