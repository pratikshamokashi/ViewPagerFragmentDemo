package com.example.viewpagerfragmentdemo.service

import com.example.viewpagerfragmentdemo.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiManger {
    @GET("users")
    fun getUserdetails(): Observable<List<UserResponse>>
}