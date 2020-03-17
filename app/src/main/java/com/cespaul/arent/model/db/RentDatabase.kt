package com.cespaul.arent.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cespaul.arent.model.RentService

@Database(entities = [RentService::class], version = 1)
abstract class RentDatabase : RoomDatabase() {
    abstract fun rentDao(): RentDao
}