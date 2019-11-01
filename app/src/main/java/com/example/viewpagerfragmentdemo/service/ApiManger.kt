package com.example.viewpagerfragmentdemo.service

import com.example.viewpagerfragmentdemo.User.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiManger {
    @GET("users")
    fun getUserdetails(): Observable<List<UserResponse>>
}