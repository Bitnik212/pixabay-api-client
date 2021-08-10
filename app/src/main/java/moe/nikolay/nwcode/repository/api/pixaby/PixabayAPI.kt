package moe.nikolay.nwcode.repository.api.pixaby

import android.util.Log
import coil.request.ImageRequest
import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PixabayAPI {
    interface SearchImageCallback {
        fun onSuccess(data: PixabayImagesModel.Response.SearchImages)
        fun onFailure(t: Throwable)
    }
    private val TAG = this::class.java.simpleName
    private val internetDataSours = PixabayAPIRequests
    private val settings = PixabaySettings()

    fun searchImage(request: PixabayImagesModel.Request.SearchImages, callback: SearchImageCallback) {
        internetDataSours.create().searchImage(
            apiKey = settings.APIKEY,
            query = request.q,
            lang = request.lang,
            id = request.id,
            image_type = request.image_type,
            orientation = request.orientation,
            category = request.category,
            min_width = request.min_width,
            min_height = request.min_height,
            colors = request.colors,
            editors_choice = request.editors_choice,
            safesearch = request.safesearch,
            order = request.order,
            page = request.page,
            per_page = request.per_page,
            callback = request.callback,
            pretty = request.pretty
        )
            .enqueue(object : Callback<PixabayImagesModel.Response.SearchImages?> {
            override fun onResponse(
                call: Call<PixabayImagesModel.Response.SearchImages?>,
                response: Response<PixabayImagesModel.Response.SearchImages?>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body() as PixabayImagesModel.Response.SearchImages)
                }
            }

            override fun onFailure(
                call: Call<PixabayImagesModel.Response.SearchImages?>,
                t: Throwable
            ) {
                Log.e(TAG, "onFailure: $t")
                callback.onFailure(t)
            }
        })
    }
}