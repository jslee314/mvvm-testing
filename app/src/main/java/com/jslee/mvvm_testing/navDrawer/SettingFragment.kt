package com.jslee.mvvm_testing.navDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater,
            R.layout.fragment_setting,container,false)
        return binding.root
    }


}