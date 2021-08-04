package moe.nikolay.nwcode.repository.api.pixaby

import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPIRequests {

    @GET("/api/")
    fun searchImage(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Call<PixabayImagesModel.Response.SearchImages>

    companion object {
        private val settings: PixabaySettings
            get () {
                val api = PixabaySettings()
                return api
            }

        fun create(): PixabayAPIRequests {
            val retrofit: Retrofit = settings.getRetrofitInstance()
            return retrofit.create(PixabayAPIRequests::class.java)
        }
    }

}