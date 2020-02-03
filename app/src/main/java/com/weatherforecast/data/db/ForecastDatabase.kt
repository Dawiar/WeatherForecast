package com.weatherforecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weatherforecast.data.OpenWeatherApiService
import com.weatherforecast.data.network.Repsonse.CityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(
    entities = [CityResponse::class],
    version = 1
)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun CityResponseDao(): CityResponseDao

    companion object{
        @Volatile private var instance: ForecastDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: buildDatabase(context).also { instance = it
                val apiService = OpenWeatherApiService()

                var currentWeatherResponse : List<CityResponse>
                GlobalScope.launch ( Dispatchers.Main ) {
                    currentWeatherResponse = apiService.getCities()
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .build()
    }

}