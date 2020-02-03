package com.weatherforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecast.data.network.Repsonse.CityResponse

class CityRecyclerViewAdapter(val context: Context, val cityList: List<CityResponse>) : RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_city, parent,false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList.get(position)
        holder.name.text = city.name+","+city.country
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.city_name)
    }
}


