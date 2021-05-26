package com.jslee.mvvm_testing.people.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentPeopleDetailBinding


class PeopleDetailFragment : Fragment(){

    private lateinit var binding : FragmentPeopleDetailBinding
    private lateinit var viewModel: PeopleDetailViewModel
    private lateinit var viewModelFactory: PeopleDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_people_detail, container,false)
        setUpBinding()

        return binding.root
    }

    private fun setUpBinding(){
        val application = requireNotNull(this.activity).application

        val peopleProperty = PeopleDetailFragmentArgs.fromBundle(
                arguments!!
        ).selectedPeople

        viewModelFactory =
                PeopleDetailViewModelFactory(
                        peopleProperty,
                        application
                )

        viewModel =  ViewModelProvider(this, viewModelFactory).get(PeopleDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }



}