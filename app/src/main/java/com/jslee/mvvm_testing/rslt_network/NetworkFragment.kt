package com.jslee.mvvm_testing.rslt_network

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.databinding.FragmentNetworkBinding
import com.jslee.mvvm_testing.util.retrofit.MarsApiFilter

class NetworkFragment : Fragment() {
    private lateinit var binding: FragmentNetworkBinding
    private val viewModel: NetworkViewModel by lazy {
        ViewModelProvider(this).get(NetworkViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_network,container,false)

        setUpBinding()
        setUpView()
        setUpObserver()

        return binding.root
    }

    private fun setUpBinding(){

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

    }

    private fun setUpView(){

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        // 필터링 옵션메뉴 set을 true로
        setHasOptionsMenu(true)

    }

    private fun setUpObserver() {
        // button에 navigaion.xml에서 만든 액션 추가
        binding.failBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_networkFragment_to_quizFragment)
        }

    }


    /**
     * 필터링 옵션이 포함 된 "오버플로 메뉴"를 오버라이드합니다.      */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    /**
     * "오버플로 메뉴"에서 메뉴 항목을 선택하면
     * 선택한 항목에따라 [ViewModel]에서 필터값을 업데이트합니다.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }

}