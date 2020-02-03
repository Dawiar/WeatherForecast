package com.weatherforecast.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.weatherforecast.data.network.Repsonse.CityResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


const val APPID = "19c09f2fe4cb431e4fd6c2b0d7bdd582"

interface OpenWeatherApiService {


    @GET("F61Wq/31a20abc67.json")
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json"
    )
    suspend fun getCities(): List<CityResponse>

    companion object {
        operator fun invoke():OpenWeatherApiService {
            val requestInterceptor = Interceptor{chain ->
                val req = chain.request()
                val res:Response = chain.proceed(req)
                Log.d("test",chain.request().body.toString())

                return@Interceptor res.newBuilder()
                 //  .header("Content-Encoding", "gzip")
                  //  .header("Content-Type","application/json;charset=utf-8")
                  //  .header("Accept","application/json")
                    .build()
            }

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(requestInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://puu.sh/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(OpenWeatherApiService::class.java)
        }
    }


 /*   private class UnzippingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            return unzip(response)
        }
    }

    private fun unzip(response: Response) : Response{
        response.body?.let {
            val responseBody = GzipSource(response.body!!.source())
            val contentLenght = response.body!!.contentLength()
            val strippedHeaders: Headers = response.headers.newBuilder()
                .removeAll("Content-Encoding")
                .removeAll("Content-Length")
                .build()
            return response.newBuilder()
                .headers(strippedHeaders)
                .body(RealResponseBody(strippedHeaders, contentLenght , Okio.buffer(responseBody)))
                .build()
        }
    }*/

}