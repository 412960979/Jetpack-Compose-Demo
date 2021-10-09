package com.wn.compose.di

import com.wn.compose.api.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { headerInterceptor() }
    single { okhttpClient(get()) }
    single { retrofit(get()) }
    single { apiService(get()) }
}

fun apiService(
    retrofit: Retrofit
): Api =
    retrofit.create(Api::class.java)

fun retrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
//        .baseUrl(BuildConfig.BASE_URL)
        .baseUrl("https://www.baidu.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient(
    headerInterceptor: Interceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

fun headerInterceptor(): Interceptor =
    Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
//            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .addQueryParameter("api_key", "123456")
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }