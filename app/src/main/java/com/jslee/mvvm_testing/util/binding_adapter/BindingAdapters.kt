package com.jslee.mvvm_testing.util.binding_adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.data.remote.GroundProperty
import com.jslee.mvvm_testing.ground.PhotoGridAdapter
import com.jslee.mvvm_testing.data.vo.retrofitCallStatus

/**
 * Glide 라이브러리를 사용해서 이미지를 로드하는 기능
 *  URL 스트링을 -> [ImageView]에 로드함  */
@BindingAdapter("BA_imageUrl")
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

/**
 * Glide를 사용하여 URL에서 이미지를 표시하는 데 사용되는 바인딩 어댑터 */
@BindingAdapter("BA_set_imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

/** [GroundProperty] 데이터가없는 경우 (데이터가 null) [RecyclerView]를 숨기고
 * 그렇지 않으면 표시 하는 기능
 */
@BindingAdapter("BA_listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GroundProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


/**
 * ImageView에 네트워크 요청에 따라 [retrofitCallStatus] 값을 표시한다
 * 요청이 로드되면 > loading_animation이 표시
 * 요청에 오류가있는 경우 > 연결 오류를 반영하기 위해 깨진 이미지가 표시
 * 요청이 완료되면 > 이미지보기를 숨 깁니다.
 */
@BindingAdapter("BA_retrofitCallStatus")
fun bindStatus(statusImageView: ImageView, status: retrofitCallStatus?) {
    when (status) {
        retrofitCallStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        retrofitCallStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        retrofitCallStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("BA_isNetworkError", "BA_playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}



