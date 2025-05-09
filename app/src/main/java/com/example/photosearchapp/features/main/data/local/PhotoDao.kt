package com.example.photosearchapp.features.main.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoList(
        photoEntities: List<PhotoEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoItem(
        photoItem: PhotoEntity
    )

    @Update
    suspend fun updatePhotoItem(
        photoItem: PhotoEntity
    )

    @Query(
        """
            DELETE FROM photoEntity
            WHERE id = :id
        """
    )
    suspend fun deletePhoto(id: Int)


    @Query("SELECT * FROM photoEntity WHERE id = :id")
    suspend fun getPhotoById(id: Int): PhotoEntity

    @Query(
        """
            SELECT *
            FROM photoEntity
        """
    )
    suspend fun getPhotoList(

    ): List<PhotoEntity>
}