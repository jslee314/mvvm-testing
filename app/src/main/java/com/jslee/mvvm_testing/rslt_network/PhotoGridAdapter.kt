package com.jslee.mvvm_testing.rslt_network

import com.jslee.mvvm_testing.databinding.GridViewItemBinding
//
//class PhotoGridAdapter ( private val onClickListener: OnClickListener) : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {
//
//    class MarsPropertyViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(marsProperty: MarsProperty) {
//            binding.property = marsProperty
//            //이는 데이터 바인딩이 즉시 실행되도록하기 때문에 중요
//            //RecyclerView가 올바른 뷰 크기 측정을 수행 할 수 있음
//            binding.executePendingBindings()
//        }
//    }
//
//    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
//        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//
//
//    /**
//     * @내용 : 새로운 [RecyclerView] 항목보기 만들기 (layout manager에서 호출 함)
//     *        어댑터의 데이터 세트를 초기화
//     *        항목 구성을 위해 사용할 ViewHolder 객체가 필요할 때 호출되는 메서드
//     * @최초작성일 : 2021-05-13 오후 1:56
//     * @작성자 : 이재선
//     **/
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
//        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
//
//    }
//
//
//    /**
//     * @내용 : [RecyclerView] 항목의 내용을 대체 (layout manager에 의해 호출 됨).
//     *        어댑터의 데이터 세트를 초기화
//     * @최초작성일 : 2021-05-13 오후 1:56
//     * @작성자 : 이재선
//     **/
//    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
//        val marsProperty = getItem(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(marsProperty)
//        }
//        holder.bind(marsProperty)
//    }
//
//
//    /**
//     * [RecyclerView] 항목에 대한 클릭을 처리하는 사용자 정의 리스너.
//     * 현재 항목과 관련된 [MarsProperty]를 [onClick] 함수에 전달합니다.
//     * @param clickListener : 현재 [MarsProperty]와 함께 호출 될 람다
//     */
//    class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
//        fun onClick(marsProperty:MarsProperty) = clickListener(marsProperty)
//    }
//}