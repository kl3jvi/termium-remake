package com.kl3jvi.termium.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.kl3jvi.termium.R
import com.kl3jvi.termium.data.InputData
import okhttp3.*
import java.io.IOException

class IPGeolocator : Fragment() {
    lateinit var ipEditText: EditText
    lateinit var ip: TextView
    lateinit var type: TextView
    lateinit var continentName: TextView
    lateinit var city: TextView
    lateinit var latitude: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.ip_fragment, container, false)
        ipEditText = view.findViewById(R.id.ipAddress)

        ip = view.findViewById(R.id.ipp)
        type = view.findViewById(R.id.hostname)
        continentName = view.findViewById(R.id.continent)
        city = view.findViewById(R.id.city)
        latitude = view.findViewById(R.id.coordinates)


        val lookupButton: Button = view.findViewById(R.id.lookup)
        lookupButton.setOnClickListener { fetchJson() }
        return view
    }

    private fun fetchJson() {
        var input: String = ipEditText.text.toString()
        if (input.isEmpty()) {
            input = "0"
        }
        Log.i("Fetching Json", input)
        val accessKey = "3eb31287efd82a01bde70e05a257965b"
        var url: String = if (input.isEmpty()) {
            "http://api.ipstack.com/check?access_key=$accessKey"
        } else "http://api.ipstack.com/${input}?access_key=$accessKey"
        println(url)
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val data = gson.fromJson(body, InputData::class.java)
                ip.text = data.ip
                type.text = data.type
                continentName.text = data.continent_name
                city.text = data.city
                latitude.text = data.latitude
            }

            override fun onFailure(call: Call, e: IOException) {
                e.message?.let { Log.i("Error", it) }
            }
        })
    }
}
