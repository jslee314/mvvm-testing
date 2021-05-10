package com.jslee.retrofittesting.success

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jslee.retrofittesting.R
import kotlinx.android.synthetic.main.recycler_view_list.view.*

/**
 * 부모 클래스인 RecyclerView.Adapter 클래스를 만들때 자료형을 결정하지 않았는데(제네릭)
 * 자식클래스로 상속하면서 MyRecyclerAdapter.ViewHolderClass 자료형(->자식클래스에서 구현한 자료형)으로 결정하면서 상속함 */
class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    var imgRes:Array<Int> = emptyArray()
    var dataKR:Array<String> = emptyArray()
    var dataENText:Array<String> = emptyArray()

    /**
     * <<<<< ViewHolder 클래스 >>>>>
     * 사용중인 Veiw의 유형에 대한 참조를 제공
     * (custom {@link RecyclerView.ViewHolder}).*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 항목 View 내부의 View 객체의 주소값을 담는다.
        val rowImage1 = itemView.rowImageView
        val rowImage2 = itemView.rowImageView2
        val rowText1 = itemView.rowTextView1
        val rowText2 = itemView.rowTextView2

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.recycler_view_list, parent, false)

                return ViewHolder(view)


            }
        }
    }

    /**
     * 어댑터의 데이터 세트를 초기화
     * 항목 구성을 위해 사용할 ViewHolder 객체가 필요할 때 호출되는 메서드
    * @param dataSet String []은 RecyclerView에서 사용할 뷰를 채우기위한 데이터를 포함 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list, null)
//        return ViewHolder(itemView)

        return ViewHolder.from(parent)

    }


    /**
     * 데이터 세트의 크기를 반환 (레이아웃 관리자가 호출 함). */
    override fun getItemCount(): Int {
        return imgRes.size
    }


    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.rowImage1.setImageResource(imgRes[position])
        holder.rowText1.text = dataKR[position]
        holder.rowText2.text = dataENText[position]
    }






}