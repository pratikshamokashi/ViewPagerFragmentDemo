package com.example.viewpagerfragmentdemo.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {


    init {
        retrofit = retrofitInstance
    }
    companion object {
        private var apiService : ApiClient? = null
        var retrofit: Retrofit? = null
        val instance : ApiClient
            get(){
                if(apiService == null){
                    apiService = ApiClient()
                }
                return apiService as ApiClient
            }
    }
    val retrofitInstance : Retrofit
        get() {
            retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .client(clientInstance)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit as Retrofit
        }

    private val clientInstance: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(3, TimeUnit.MINUTES)
            httpClient.readTimeout(3, TimeUnit.MINUTES)
            httpClient.writeTimeout(3, TimeUnit.MINUTES)
            return httpClient.build()
        }

    internal val apiServices: ApiManger
        get() = retrofit!!.create(ApiManger::class.java)


}