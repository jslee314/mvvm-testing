package com.jslee.retrofittesting.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
        R.layout.fragment_home,container,false)
        return binding.root
        }


}