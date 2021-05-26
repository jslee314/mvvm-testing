package com.jslee.mvvm_testing.people

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jslee.mvvm_testing.MyApplication
import com.jslee.mvvm_testing.databinding.FragmentPeopleBinding
import javax.inject.Inject

class PeopleFragment : Fragment() {
    private lateinit var binding: FragmentPeopleBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PeopleViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication)
                .appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentPeopleBinding.inflate(inflater)

        setUpBinding()
        setUpView()
        setUpObserver()

        return binding.root
    }
    private fun setUpBinding(){
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }
    private fun setUpView(){}

    private fun setUpObserver() {}
}