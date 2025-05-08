package com.example.photosearchapp.features.main.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PhotoEntity::class],
    version = 1, exportSchema = false
)

abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDao: PhotoDao
}