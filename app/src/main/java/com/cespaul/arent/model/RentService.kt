package com.cespaul.arent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rentService")
data class RentService(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    var nameService: String,
    @ColumnInfo(name = "rate")
    var rateService: Float,
    @ColumnInfo(name = "amount")
    var amountService: Float,
    @ColumnInfo(name = "sum")
    var sumService: Float
)