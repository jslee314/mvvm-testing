package com.jslee.mvvm_testing.rslt_network

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentNetworkBinding

class NetworkFragment : Fragment() {
    private lateinit var binding: FragmentNetworkBinding
    private lateinit var viewModel: NetworkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_network,container,false)

        viewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        // button에 navigaion.xml에서 만든 액션 추가
        binding.failBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_networkFragment_to_quizFragment)
        }

        return binding.root
    }

}