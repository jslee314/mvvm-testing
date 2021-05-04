package com.jslee.retrofittesting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.jslee.retrofittesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val user: User = User("jslee")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.user = user

        binding.doneButton.setOnClickListener{
            addName(it)
        }

    }

    private fun addName(view: View){
        binding.apply {
            user?.age = ageEditText.text.toString()

            invalidateAll() // data가 변한 후 연결된 view들에 변화를 알려주는 함수
            ageEditText.visibility = View.GONE
            doneButton.visibility = View.GONE
            ageText.visibility = View.VISIBLE
        }

        // 키보드 숨기기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}