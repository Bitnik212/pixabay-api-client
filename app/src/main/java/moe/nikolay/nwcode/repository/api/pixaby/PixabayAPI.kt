package moe.nikolay.nwcode.repository.api.pixaby

import android.util.Log
import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PixabayAPI {
    private val TAG = this::class.java.simpleName
    private val internetDataSours = PixabayAPIRequests
    private val settings = PixabaySettings()

    fun searchImage() {
        internetDataSours.create().searchImage(apiKey = settings.APIKEY, query = "cat").enqueue(object : Callback<PixabayImagesModel.Response.SearchImages?> {
            override fun onResponse(
                call: Call<PixabayImagesModel.Response.SearchImages?>,
                response: Response<PixabayImagesModel.Response.SearchImages?>
            ) {
                Log.d(TAG, "onResponse: code is ${response.code()} body is ${response.body().toString()}")
            }

            override fun onFailure(
                call: Call<PixabayImagesModel.Response.SearchImages?>,
                t: Throwable
            ) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }
}