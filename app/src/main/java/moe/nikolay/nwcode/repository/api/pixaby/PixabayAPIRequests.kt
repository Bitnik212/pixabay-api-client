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
        @Query("q") query: String,
        @Query("lang") lang: String,
        @Query("id") id: String,
        @Query("image_type") image_type: String,
        @Query("orientation") orientation: String,
        @Query("category") category: String,
        @Query("min_width") min_width: Int,
        @Query("min_height") min_height: Int,
        @Query("colors") colors: String,
        @Query("editors_choice") editors_choice: Boolean,
        @Query("safesearch") safesearch: Boolean,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("callback") callback: String,
        @Query("pretty") pretty: Boolean,
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