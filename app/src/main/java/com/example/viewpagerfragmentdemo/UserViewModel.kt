package com.example.viewpagerfragmentdemo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewpagerfragmentdemo.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserViewModel(val context: Context) :ViewModel() {

    val userlist: MutableLiveData<List<UserResponse>> = MutableLiveData()
    fun userList(): MutableLiveData<List<UserResponse>>? {
        Log.d("Tag","Check2:")

        ApiClient().apiServices.getUserdetails().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    Log.d("Tag","Check3:")

                    if (it != null) {
                        userlist.postValue(it)
                    }
                },
                onError = {
                    userlist.postValue(null)
                },
                onComplete = {

                }
            )
    return null
    }

    var favlist: LiveData<List<FavEntity>> ?= null
    fun favlist(): LiveData<List<FavEntity>>? {
        Log.i("VVV", " live data: "+DemoApplication.getInstance()?.getDatabase()?.favDao()?.getFavList(true))
       favlist = DemoApplication.getInstance()?.getDatabase()?.favDao()?.getFavList(true)!!
        return favlist
    }



    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return UserViewModel(context) as T
        }
    }
}