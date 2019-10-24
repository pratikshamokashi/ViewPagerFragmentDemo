package com.example.viewpagerfragmentdemo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavList(favEntity: FavEntity)

    @Query("select * from FavEntity")
    fun getFavList():LiveData<List<FavEntity>>

    @Query("DELETE FROM favEntity WHERE id =:favId")
    fun deleteFav(favId: Int?)

    @Query("UPDATE FavEntity SET isFav = :isFav WHERE id = :id")
    fun updateUserById(isFav: Boolean, id: Int)

}