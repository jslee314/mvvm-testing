package com.jslee.mvvm_testing.rslt_network_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentNetwrokDetailBinding

class NetworkDetailFragment : Fragment(){

    private lateinit var binding : FragmentNetwrokDetailBinding
    private lateinit var viewModel: NetworkDetailViewModel
    private lateinit var viewModelFactory: NetworkDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_netwrok_detail, container,false)
        setUpBinding()

        return binding.root
    }

    private fun setUpBinding(){
        val application = requireNotNull(this.activity).application

        val groundProperty = NetworkDetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        viewModelFactory = NetworkDetailViewModelFactory(groundProperty, application)

        viewModel =  ViewModelProvider(this, viewModelFactory).get(NetworkDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }


}