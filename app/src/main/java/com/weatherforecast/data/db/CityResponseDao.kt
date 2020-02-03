package com.weatherforecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherforecast.data.network.Repsonse.CityResponse


@Dao
interface CityResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(cityResponse: CityResponse)

    @Query("select * from cities_list where id =:id ")
    fun getCity(id: Int) : LiveData<CityResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityList(cityResponse: List<CityResponse>)
}