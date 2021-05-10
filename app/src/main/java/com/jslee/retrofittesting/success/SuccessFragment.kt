package com.jslee.retrofittesting.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jslee.retrofittesting.R
import com.jslee.retrofittesting.databinding.FragmentSuccessBinding
import com.jslee.retrofittesting.data.local.RoomDB


class SuccessFragment : Fragment() {

    private lateinit var binding : FragmentSuccessBinding
    private lateinit var viewModel: SuccessViewModel
    private lateinit var viewModelFactory: SuccessViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success,container,false)

        setUpBinding()
        setUpView()
        setUpObserver()

        return binding.root
    }

    /**
    * @내용 :
    * @최초작성일 : 2021-05-10 오후 6:30
    * @작성자 : 이재선
    **/
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

    /**
    * @내용 :
    * @최초작성일 : 2021-05-10 오후 6:30
    * @작성자 : 이재선
    **/
    private fun setUpView(){

        // 이미지RecyclerView 만들기
        val imageAdapter = ImageAdapter()
        binding.imageRecyclerView.adapter = imageAdapter
        binding.imageRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val imgRes = arrayOf(R.drawable.food_icon_barbecue, R.drawable.food_icon_beer,
            R.drawable.food_icon_bobatea, R.drawable.food_icon_burger, R.drawable.food_icon_coffee,
            R.drawable.food_icon_cupcake, R.drawable.food_icon_donut, R.drawable.food_icon_noodle,
            R.drawable.food_icon_pizza, R.drawable.food_icon_steak)

        val dataKR = arrayOf("바베큐", "맥주", "버블티", "버거", "커피",
            "컵케이크", "도넛", "국수", "피자", "스테이크")

        val dataENText = arrayOf("barbecue is delicious", "beer is delicious", "bobatea is delicious",
            "burger is delicious", "coffee is delicious", "cupcake is delicious",
            "donut is delicious", "noodle is delicious", "pizza is delicious", "steak is delicious")

        imageAdapter.imgRes = imgRes
        imageAdapter.dataKR = dataKR
        imageAdapter.dataENText = dataENText
        imageAdapter.notifyDataSetChanged()

    }

    /**
    * @내용 :
    * @최초작성일 : 2021-05-10 오후 6:30
    * @작성자 : 이재선
    **/
    private fun setUpObserver() {

        /**
         * 성공 버튼 클릭여부에 대한 라이브데이터를 observing */
        viewModel.eventClickSuccess.observe(viewLifecycleOwner, Observer<Boolean> { isSuccessed ->
            if (isSuccessed) {
                NavHostFragment.findNavController(this)
                    .navigate(SuccessFragmentDirections.actionSuccessFragmentToQuizFragment())
            }
        })


    }

}