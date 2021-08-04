package moe.nikolay.nwcode.repository.api.pixaby

import moe.nikolay.nwcode.BuildConfig
import moe.nikolay.nwcode.helper.Exceptions.ExceptionsInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class PixabaySettings {
    private val hostname = "pixabay.com"
    val apiLink = "$hostname/api/"
    val APIKEY = BuildConfig.APIKEY

    private var okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(ExceptionsInterceptor())
        .build()

    fun getRetrofitInstance(): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://$apiLink")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}