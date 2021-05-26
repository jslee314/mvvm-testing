package com.jslee.mvvm_testing.quiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jslee.mvvm_testing.MyApplication
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentQuizBinding
import com.jslee.mvvm_testing.data.local.AppDatabase
import com.jslee.mvvm_testing.home.HomeViewModel
import javax.inject.Inject


class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<QuizViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication)
            .appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz,container,false)

        setUpBinding()
        setUpObserver()

        return binding.root
    }

    private fun setUpBinding(){

        // databinding을 위한 viewmodel 셋팅 -VieWModel의 모든 데이터에 바인딩 된 레이아웃 액세스를 허용
        binding.viewModel = viewModel

        //binding의 lifecycle owner로 fragment view를 지정 -> 이로써,,바인딩이 LiveData 업데이트를 관찰 할 수 있도함
        binding.lifecycleOwner = viewLifecycleOwner

    }

    private fun setUpObserver(){
        viewModel.eventToNetworkBtn.observe(viewLifecycleOwner, Observer<Boolean> { isClicked ->
            if(isClicked){
                viewModel.insertToRoomDB()
                var action = QuizFragmentDirections.actionQuizFragmentToGroundFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        })

        viewModel.eventClickToRoomBtn.observe(viewLifecycleOwner, Observer<Boolean> { isClicked ->
            if(isClicked){
                viewModel.insertToRoomDB()
                var action =  QuizFragmentDirections.actionQuizFragmentToPersonFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        })
    }



}