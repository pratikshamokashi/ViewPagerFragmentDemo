package com.example.viewpagerfragmentdemo

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.viewpagerfragmentdemo.service.ApiClient.Companion.instance


@Database(entities = [(FavEntity::class)],version = 1)
abstract class FavDB :RoomDatabase() {


companion object {
    var instance: FavDB? = null
    fun getDatabase(context: Context): FavDB? {
        if (instance == null) {
            synchronized(FavDB::class.java) {
                if (instance == null) {
                    // Create database here
                    instance = Room.databaseBuilder(
                        context,
                        FavDB::class.java!!, "hh_database"
                    )
                        .build()

                    Log.i("USER", "Database created")
                }
            }
        }
        return this.instance
    }
}
    abstract fun favDao() : FavDAO
}