package com.jslee.mvvm_testing.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.databinding.VideoItemBinding

/**
 * 목록의 항목에 데이터 바인딩을 설정하기위한 RecyclerView Adapter  */
class VideoAdapter(val callback: VideoClick) :
                    RecyclerView.Adapter<VideoViewHolder>(){

    /**
     * 어댑터가 보여줄 비디오 데이터 리스트 */
    var videos: List<DevByteVideo> = emptyList()
        set(value) {
            field = value // 페이징 라이브러리를 사용할수있음

            // 등록 된 관찰자에게 데이터 세트가 변경되었음을 알립니다.
            // 이렇게하면 RecyclerView의 모든 요소가 무효화됩니다.
            notifyDataSetChanged()
        }

    /**
     * RecyclerView가 항목을 나타내기 위해 지정된 유형의 새로운 {@link ViewHolder}가 필요할 때 호출됩니다.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val withDataBinding: VideoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            VideoViewHolder.LAYOUT,
            parent,
            false)
        return VideoViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size

    /**
     * RecyclerView에서 호출하여 지정된 position에 데이터를 표시합니다.
     * 이 함수는 {@link ViewHolder # itemView}의 콘텐츠를 업데이트하여 지정된 위치의 항목을 반영해야합니다.*/
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }
}

/**
 * DevByte 항목에 대한 ViewHolder. 모든 작업은 데이터 바인딩으로 수행됩니다. */
class VideoViewHolder(val viewDataBinding: VideoItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.video_item
    }
}

/**
 * 비디오에 대한 클릭 리스너.
 * clkListener에 이름을 지정하면 reader가 clkListener(람다)의 기능을 이해하는 데 도움이됩니다. */
class VideoClick(val clkListener: (DevByteVideo) -> Unit) {
    /**
     * Called when a video is clicked
     *
     * @param clkListener the video that was clicked
     */
    fun onClick(video: DevByteVideo) = clkListener(video)
}