package com.example.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchPlaces(query: String) = liveData (Dispatchers.IO){
        Log.d("SEARCH_LOG", "Repository 开始请求，query=$query")
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlace(query)
            Log.d("SEARCH_LOG", "接口返回status=${placeResponse.status}")
            if (placeResponse.status == "ok"){
                val places = placeResponse.places ?: emptyList()
                Log.d("SEARCH_LOG", "成功拿到地点数量：${places.size}")
                Log.d("SEARCH_LOG", "原始返回json: ${placeResponse}")
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        }catch (e: Exception){
            Log.e("SEARCH_LOG", "请求异常", e)
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}