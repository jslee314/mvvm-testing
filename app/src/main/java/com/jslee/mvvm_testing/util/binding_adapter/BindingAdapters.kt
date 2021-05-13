package com.jslee.mvvm_testing.util.binding_adapter

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.data.remote.MarsProperty
import com.jslee.mvvm_testing.rslt_network.PhotoGridAdapter

/**
 * Glide 라이브러리를 사용해서 이미지를 로드하는 기능을 함
 *  URL 스트링을 -> [ImageView]에 로드함
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    // imgUrl이 null 이 아닐경우 수행 >>>>>  let() + ?.
    imgUrl?.let {

        // url string 에서 Uri 객체로 변환(To use the HTTPS scheme)
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)         // Uri 개체에서 ImageView로 이미지를 로드함 with().load(imgUri).into(imgView)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)  // 이미지 로드를 하는 도중에 나오는 이미지
                .error(R.drawable.ic_broken_image))         // 이미지 로드를 실패했을 때 나오는 이미지
            .into(imgView)
    }
}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}