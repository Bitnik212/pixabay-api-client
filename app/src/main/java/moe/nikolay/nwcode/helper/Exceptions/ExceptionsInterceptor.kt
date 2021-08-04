package moe.nikolay.nwcode.helper.Exceptions

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import java.io.IOException


class ExceptionsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .proceed(chain.request())
        .also { getError(it, it.body())?.let { exception -> throw exception } }

    private fun getError(response: Response, body: ResponseBody?): IOException? = body
        ?.let { responseBody ->
            try {
                when {
                    response.code() != 200 -> ServerException(response.code())
                    else -> null
                }
            } catch (error: JSONException) {
                null
            }
        }


}