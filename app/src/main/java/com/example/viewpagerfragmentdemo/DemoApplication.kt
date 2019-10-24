package com.example.viewpagerfragmentdemo

import android.app.Application
import android.util.Log

class DemoApplication :Application() {
    private var database: FavDB? = null
  companion object {
      var  mInstance :DemoApplication ?= null

      @Synchronized
      fun getInstance(): DemoApplication? {
          return mInstance
      }
  }
    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    fun getDatabase(): FavDB? {
        database = FavDB.getDatabase(getInstance()!!)
        Log.d("VVV", "database"+database.toString())
        return this.database
    }

}