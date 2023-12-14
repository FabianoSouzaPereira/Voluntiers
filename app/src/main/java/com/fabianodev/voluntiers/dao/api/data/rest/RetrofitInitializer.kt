package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.utils.Constants
import com.fabianodev.voluntiers.utils.Constants.API_SERVER_NAME
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    private val timeout: Long = Constants.CONNECTTIMEOUT.toLong()
    private val readTimeout: Long = Constants.READTIMEOUT.toLong()
    private val interseptor: RedirectInterceptor = RedirectInterceptor()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .protocols(Collections.singletonList(Protocol.HTTP_1_1))
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder().client(okHttpClient)
        .baseUrl(API_SERVER_NAME)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {

        @JvmStatic
        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder().client(RetrofitInitializer().okHttpClient)
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}

/** This interceptor request to avoid redirect authenticationmodel on 307 by response */
open class RedirectInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var response: Response = chain.proceed(chain.request())

        if (response.code() == 307) {
            request = response.header("Location")?.let {
                val first = it.slice(0..0)
                val url = if (first == "/") it.substring(1) else it
                request.newBuilder()
                    .url(API_SERVER_NAME + url)
                    .build()
            }!!
            response = chain.proceed(request)
        }
        return response
    }
}