package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.utils.Constants
import com.fabianodev.voluntiers.utils.Constants.API_SERVER_AUTH
import com.fabianodev.voluntiers.utils.Constants.API_SERVER_NAME
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitInitializer(token: String) {
    private val timeout: Long = Constants.CONNECTTIMEOUT.toLong()
    private val readTimeout: Long = Constants.READTIMEOUT.toLong()
    private val interseptor: RedirectInterceptor = RedirectInterceptor(token)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interseptor)
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
        fun getRetrofitInstance(path: String, token: String): Retrofit {
            return Retrofit.Builder().client(RetrofitInitializer(token).okHttpClient)
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}

/** This interceptor request to avoid redirect authenticationmodel on 307 by response */
open class RedirectInterceptor(tokenInterceptor: String) : Interceptor {
    private var token = tokenInterceptor

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        var modifiedRequest: Request = originalRequest
        if (!originalRequest.url().toString().contentEquals(API_SERVER_AUTH)) {
            modifiedRequest = token.let {
                originalRequest.newBuilder()
                        .header("Authorization", "Bearer $it")
                        .build()
            } ?: originalRequest
        }
        var response: Response = chain.proceed(modifiedRequest)

//        if (response.code() == 307) {
//            modifiedRequest = response.header("Location")?.let {
//                val first = it.slice(0..0)
//                val url = if (first == "/") it.substring(1) else it
//                modifiedRequest.newBuilder()
//                        .url(API_SERVER_NAME + url)
//                        .build()
//            }!!
//            response = chain.proceed(modifiedRequest)
//        }
        return response
    }
}