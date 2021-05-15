package com.jslee.mvvm_testing.rslt_network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jslee.mvvm_testing.data.remote.GroundProperty
import com.jslee.mvvm_testing.databinding.GridViewItemBinding

/**
 * [RecyclerView]: ListView와는 다르게 RecyclerView는 이름에서 알 수 있듯이 재활용이 가능한 뷰이다.
 * ListView는 스크롤할때마다 맨 위의 뷰 객체가 삭제되고, 아래 나타날 객체는 새로 생성하는원리로.. 스크롤을 움직이면 삭제/생성이 반복된다.
 * 반면 RecyclerView는 사용자가 아래로 스크롤 한다고 가정했을 때,
 * 맨 위에 존재해서 이제 곧 사라질 뷰 객체를 삭제 하지않고, 아랫쪽에서 새로 나타나날 파란색 뷰 위치로 객체를 이동시킨다.
 * 즉 뷰 객체 자체를 재사용 하는 것인데, 중요한 점은 뷰 객체를 재사용 할 뿐이지 뷰 객체가 담고 있는 데이터(채팅방 이름)는 새로 갱신된다는 것이다.
 * 어쨋거나 뷰 객체를 새로 생성하지는 않으므로 효율적인 것이다.
 *
 * [ViewHolder] : 스크롤을 내릴때 맨 위에 존재해 사라진 객체는 맨 아래로 이동하여 재활용되고잇는데,
 * 실제 데이터의 갯수가 아닌, 화면에 '보여지는 ** 개' 뷰 객체를 만들어서 가지고 (재활용 하고) 있는것이 ViewHolder 이다.
 *
 */
class PhotoGridAdapter(private val propertyOnClickListener: MarsOnClickListener): // 주생성자 선언: OnClickListener 라는 객체 변수에 값을 무조건 설정해야만 객체가 생성될 수 있도록 강제
                    ListAdapter<GroundProperty, PhotoGridAdapter.GroundPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<GroundProperty>() {
        // // 이전 값과 같은지 비교(value와 데이터 모두 비교)
        override fun areItemsTheSame(oldItem: GroundProperty, newItem: GroundProperty): Boolean {
            return oldItem === newItem
        }

        // 가장 먼저 실행되는 함수로, 뿌려줄 데이터의 전체 길이를 리턴
        override fun areContentsTheSame(oldItem: GroundProperty, newItem: GroundProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * [ViewHolder]가 생성되는 함수다. 여기서 ViewHolder객체를 만들어 주면 된다. (layout manager에 의해 호출 됨)
     * 화면의 보여지는 뷰 객체 갯수 + 2~3개 정도 만큼만 호출되고 더이상 호출되지 않는다. **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroundPropertyViewHolder {
        val view = GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return GroundPropertyViewHolder(view)
    }

    /**
     * 생성된 [ViewHolder]에 데이터를 바인딩 해주는 함수 (layout manager에 의해 호출 됨)
     * 스크롤을 움직일때마다 데이터 바인딩이 새롭게 필요한데, 그때 마다 계속 호출된다.  **/
    override fun onBindViewHolder(holder: GroundPropertyViewHolder, position: Int) {
        val groundProperty = getItem(position) // position번째 있는 아이템(groundProperty 객체)

        /**
         * 뷰홀더가 클릭되었을 때 수행된다.
         * onClickListener 클래스의 onClick 함수의 파라미터로
         * 클릭한 position에 있는 객체 groundProperty를 함께 넘겨준다. */
        holder.itemView.setOnClickListener {
            propertyOnClickListener.onClick(groundProperty)
        }

        holder.bind(groundProperty)
    }

    /**
     *  맨 처음 화면에 보여질 **개의 뷰객체를 기억하고 있을(홀딩) 객체가가  GroundPropertyViewHolder이다.
     *  이렇게 만들어져 홀딩하고 있는 객체는 스크롤을 내릴때 삭제/생성되지 않고 재활용된다. */
    class GroundPropertyViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(groundProperty: GroundProperty) {
            //이는 데이터 바인딩이 즉시 실행되도록 해서, RecyclerView가 올바른 뷰 크기 측정을 수행 할 수 있음
            // 또한, 스크롤을 내릴때마다 이부분만 바꿔주면 뷰 객체는 그대로이면서 데이터만 바뀌게 되는것이다.
            binding.property = groundProperty
            binding.executePendingBindings()
        }
    }


    /**
     * [RecyclerView] 항목에 대한 클릭을 처리하는 사용자 정의 리스너.
     * 클릭한 position에 있는 객체 groundProperty를 [onClick] 함수에 전달한다.
     * [clkListener] : 현재 [GroundProperty]와 함께 호출 될 람다
     */
    class MarsOnClickListener(val clkListener: (groundProperty: GroundProperty) -> Unit) {
        fun onClick(groundProperty:GroundProperty) = clkListener(groundProperty)
    }
}