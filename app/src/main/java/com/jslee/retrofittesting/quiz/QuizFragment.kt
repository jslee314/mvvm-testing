package com.jslee.retrofittesting.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz,container,false)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // databinding을 위한 viewmodel 셋팅 -VieWModel의 모든 데이터에 바인딩 된 레이아웃 액세스를 허용
        binding.viewModel = viewModel


        //binding의 lifecycle owner로 fragment view를 지정 -> 이로써,,바인딩이 LiveData 업데이트를 관찰 할 수 있도함
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventSelectFinish.observe(viewLifecycleOwner, Observer<Boolean> { isFinished ->
            if(isFinished){
                Finished()
            }
        })

        return binding.root
    }
    /**
    * @내용 :
    * @수정 :
    * @버젼 : 0.0.0
    * @최초작성일 : 2021-05-06 오후 6:31
    * @작성자 : 이재선
    **/
    private fun Finished(){
        var action = QuizFragmentDirections.actionQuizFragmentToSuccessFragment()
        if(viewModel.score.equals(2)){
            action = QuizFragmentDirections.actionQuizFragmentToFailFragment()
        }
        NavHostFragment.findNavController(this).navigate(action)

    }


}