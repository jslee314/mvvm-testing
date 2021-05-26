package com.jslee.mvvm_testing.ground.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentGroundDetailBinding

class GroundDetailFragment : Fragment(){

    private lateinit var binding : FragmentGroundDetailBinding
    private lateinit var viewModel: GroundDetailViewModel
    private lateinit var viewModelFactory: GroundDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ground_detail, container,false)
        setUpBinding()

        return binding.root
    }

    private fun setUpBinding(){
        val application = requireNotNull(this.activity).application

        val groundProperty = GroundDetailFragmentArgs.fromBundle(
            arguments!!
        ).selectedProperty

        viewModelFactory =
            GroundDetailViewModelFactory(
                groundProperty,
                application
            )

        viewModel =  ViewModelProvider(this, viewModelFactory).get(GroundDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }


}