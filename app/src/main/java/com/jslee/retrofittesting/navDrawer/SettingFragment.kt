package com.jslee.retrofittesting.navDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater,
            R.layout.fragment_setting,container,false)
        return binding.root
    }


}