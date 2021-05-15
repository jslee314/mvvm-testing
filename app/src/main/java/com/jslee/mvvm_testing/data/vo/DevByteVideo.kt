package com.jslee.mvvm_testing.data.vo

import com.jslee.mvvm_testing.util.smartTruncate

/**
 * Domain 객체는 앱의 항목을 나타내는 일반 Kotlin의 data class입니다.
 * 화면에 표시되거나 앱에서 조작해야하는 개체입니다.
 *
 * @see database for database에 매핑 된 객체
 * @see network for network calls 네트워크 호출을 구문 분석하거나 준비하는 객체
 */

/**
 * Videos는 재생할 수있는 devbyte를 나타냅니다.
 */
data class DevByteVideo(val title: String,
                        val description: String,
                        val url: String,
                        val updated: String,
                        val thumbnail: String) {

    /**
     * 간단한 설명은 UI에서 잘린 설명을 표시하는 데 사용됩니다.
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}