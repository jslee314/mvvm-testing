package com.jslee.mvvm_testing.data.converter

import com.jslee.mvvm_testing.data.local.entity.Video
import com.jslee.mvvm_testing.data.vo.DevByteVideo
import com.jslee.mvvm_testing.data.vo.NetworkVideo
import com.squareup.moshi.JsonClass

/**
 * 서버의 응답을 구문 분석하거나 서버로 보낼 개체의 형식을 지정합니다.
 * 서버를 사용하기 전에 도메인 개체로 변환해야합니다. */


/**
 * asDomainModel() 확장함수는
 * Videos [데이터베이스 객체]를 DevByteVideo[도메인 객체]로 매핑 한다
 *
 * 사실 이 작업은 Video 예제에서는 필요하지 않지만,
 * 실제 앱에서는 도메인, 데이터베이스 및 네트워크 개체의 구조가 다른 경우가 많기때문에
 * 변환 맵핑이 복잡한 로직이 필요하다. */
fun List<Video>.asDomainModel(): List<DevByteVideo> {
    return map {
        DevByteVideo(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}


/**
 * VideoHolder에는 비디오 목록이 있습니다.
 *
 * 이것은 다음과 같은 네트워크 결과의 첫 번째 수준을 구문 분석하는 것입니다.
 *
 * {
 *   "videos": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)
/**
 * Convert Network results ->> database objects  */
fun NetworkVideoContainer.asDomainModel(): List<DevByteVideo> {
    return videos.map {
        DevByteVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}


/**
 * Convert Network results ->> database objects  */
fun NetworkVideoContainer.asDatabaseModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}