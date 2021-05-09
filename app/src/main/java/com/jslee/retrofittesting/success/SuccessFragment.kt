package com.jslee.retrofittesting.success

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentSuccessBinding
import com.jslee.retrofittesting.db.RoomDB
import com.jslee.retrofittesting.home.HomeViewModelFactory


class SuccessFragment : Fragment() {

    private lateinit var binding : FragmentSuccessBinding
    private lateinit var viewModel: SuccessViewModel
    private lateinit var viewModelFactory: SuccessViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success,container,false)

        setUpBinding()
        setUpObserver()

        return binding.root
    }

    private fun setUpBinding(){
        val application = requireNotNull(this.activity).application

        val userDao = RoomDB.getInstance(application).userDao
        val scoreDao = RoomDB.getInstance(application).scoreDao

        viewModelFactory = SuccessViewModelFactory(userDao, scoreDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SuccessViewModel::class.java)

        // databinding을 위한 viewmodel 셋팅 -VieWModel의 모든 데이터에 바인딩 된 레이아웃 액세스를 허용
        binding.viewModel = viewModel

        //binding의 lifecycle owner로 fragment view를 지정 -> 이로써,,바인딩이 LiveData 업데이트를 관찰 할 수 있도함
        binding.lifecycleOwner = viewLifecycleOwner

    }

    private fun setUpObserver(){
        viewModel.eventClickSuccess.observe(viewLifecycleOwner, Observer<Boolean> { isSuccessed ->
            if(isSuccessed){
                successed()
            }
        })
    }

    private fun successed(){
        NavHostFragment.findNavController(this).navigate(SuccessFragmentDirections.actionSuccessFragmentToQuizFragment())
    }

}