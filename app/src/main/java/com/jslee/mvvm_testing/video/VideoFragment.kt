package com.jslee.mvvm_testing.video

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jslee.mvvm_testing.MyApplication
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.databinding.FragmentVideoBinding
import javax.inject.Inject


class VideoFragment : Fragment() {
    private lateinit var binding : FragmentVideoBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<VideoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication)
            .appComponent.inject(this)
    }

    private var videoAdapter: VideoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentVideoBinding.inflate(inflater)

        setUpBinding()
        setUpView()
        setUpObserver()

        return binding.root
    }

    private fun setUpBinding(){

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setUpView(){

        videoAdapter = VideoAdapter(VideoClick {
            // 비디오를 클릭하면이 clkListener(람다)는 VideoAdapter에 의해 호출됨

            // context가 없는 경우 Fragment가 더 이상 화면에 표시되지 않으므로 [VideoClick]을 안전하게 버릴 수 있음
            val packageManager = context?.packageManager ?: return@VideoClick

            // YouTube 앱에 대한 직접적인 intent 생성
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if (intent.resolveActivity(packageManager) == null) {
                // YouTube 앱을 찾을 수 없으면, 웹 URL을 사용
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

    }

    private fun setUpObserver(){

        // videos 값을 옵저빙
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { videos ->
            videos?.apply {
                videoAdapter?.videos = videos
            }
        })

        // network error를 옵저빙
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

    }

    /**
     * Method for displaying a Toast error message for network errors. */
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


    /**
     * Helper method to generate YouTube app links  */
    private val DevByteVideo.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }

}