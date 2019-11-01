package com.example.viewpagerfragmentdemo.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class UserEntity {

        @PrimaryKey
        var id :Int = 0
        @ColumnInfo(name = "name")
        var name :String =""
        @ColumnInfo(name = "username")
        var username :String =""
        @ColumnInfo(name ="email")
        var email :String=""
        @ColumnInfo(name="isFav")
        var isFav :Boolean =false
    }
