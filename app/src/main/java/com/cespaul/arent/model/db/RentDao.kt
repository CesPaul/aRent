package com.cespaul.arent.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cespaul.arent.model.RentService

@Dao
interface RentDao {

    @Insert
    fun insertService(service: RentService)

    @Query("select * from rentService")
    fun selectServices(): List<RentService>

    @Query("delete from rentService where id == :id")
    fun deleteService(id: Int)

}