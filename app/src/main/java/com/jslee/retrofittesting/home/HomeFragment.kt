package com.jslee.retrofittesting.home

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
import com.jslee.retrofittesting.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

        private lateinit var binding :FragmentHomeBinding
        private lateinit var viewModel: HomeViewModel


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

                binding = DataBindingUtil.inflate(inflater,
                        R.layout.fragment_home,container,false)

                Log.d("jjslee", "Called ViewModelProvider.get")
                viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

                // 버튼 클릭핸들러에 navigation.xml에서 만든 "액션"추가
                // 액션: action_homeFragment_to_quizFragment
                binding.button.setOnClickListener { view : View ->
                        view.findNavController().navigate(R.id.action_homeFragment_to_quizFragment)   }

                return binding.root
        }


}