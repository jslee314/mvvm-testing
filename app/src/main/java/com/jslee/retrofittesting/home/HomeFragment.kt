package com.jslee.retrofittesting.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentHomeBinding
import com.jslee.retrofittesting.db.RoomDB

class HomeFragment : Fragment() {

        private lateinit var binding :FragmentHomeBinding
        private lateinit var viewModel: HomeViewModel
        private lateinit var viewModelFactory: HomeViewModelFactory


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)

                setUpBinding()
                setUpObserver()

                return binding.root
        }

        private fun setUpBinding(){
                val application = requireNotNull(this.activity).application

                val userDao = RoomDB.getInstance(application).userDao
                val scoreDao = RoomDB.getInstance(application).scoreDao

                viewModelFactory = HomeViewModelFactory(userDao, scoreDao, application)
                viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

                // databinding을 위한 viewmodel 셋팅 -VieWModel의 모든 데이터에 바인딩 된 레이아웃 액세스를 허용
                binding.viewModel = viewModel

                //binding의 lifecycle owner로 fragment view를 지정 -> 이로써,,바인딩이 LiveData 업데이트를 관찰 할 수 있도함
                binding.setLifecycleOwner(this)

        }

        /**
        * @내용 :
        * @최초작성일 : 2021-05-08 오전 12:30
        * @작성자 : 이재선
        **/
        private fun setUpObserver(){

                /**  eventClickStart 라이브 데이터를 observing 함 */
                viewModel.eventClickStart.observe(viewLifecycleOwner, Observer<Boolean> { isClicked ->
                        if(isClicked){
                                NavHostFragment.findNavController(this).navigate(
                                        HomeFragmentDirections.actionHomeFragmentToQuizFragment())

                        }
                })
        }
}