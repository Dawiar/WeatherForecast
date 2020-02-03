package com.weatherforecast.data.network.Repsonse


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weatherforecast.data.db.Entity.Coord

@Entity(tableName = "cities_list")
data class CityResponse(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,
    @Embedded(prefix = "coord_")
    val coord: Coord

)