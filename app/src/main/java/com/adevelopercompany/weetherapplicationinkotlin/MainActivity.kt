package com.adevelopercompany.weetherapplicationinkotlin

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tvTemp: TextView
    lateinit var tvDesc: TextView
    lateinit var tvHumidity: TextView
    lateinit var tvPressure: TextView
    lateinit var btnSearch: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etCity = findViewById<EditText>(R.id.etCity)
        tvTemp = findViewById(R.id.tvTem)
        tvDesc = findViewById(R.id.tvDescription)
        tvHumidity = findViewById(R.id.tvHumidity)
        btnSearch = findViewById(R.id.ivSearch)
        tvPressure = findViewById(R.id.tvPressure)
        btnSearch.setOnClickListener {
            val name: String = etCity.getText().toString()

            if (TextUtils.isEmpty(name)) {
                etCity.setError("Enter city")
                return@setOnClickListener
            }

            getWeatherData(name)
        }

    }

    private fun getWeatherData(name: String) {

        val apiClint = ApiClint()
        val apiInterface: ApiInterface = apiClint.getClint()!!.create(ApiInterface::class.java)
        val call = apiInterface.getWeatherData(name)

        call!!.enqueue(object : Callback<Example?> {
            override fun onResponse(call: Call<Example?>?, response: Response<Example?>) {
                //  val statusCode: Int = response.code()
                if (response.isSuccessful) {
                    tvTemp.text = response.body()!!.main!!.temp + " C"
                    tvDesc.text = response.body()!!.main.feels_like
                    tvHumidity.text = response.body()!!.main.humidity
                    tvPressure.text = response.body()!!.main.pressure
                    Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG)
                        .show()
                    //  Log.d("response",response.body()!!.getMain()!!.getMix_temp())

                } else {
                    Toast.makeText(this@MainActivity, "Enter Correct City Name", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Example?>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_SHORT)
                    .show()
            }
        })


    }
}