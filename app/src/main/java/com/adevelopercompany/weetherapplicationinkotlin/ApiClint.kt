package com.adevelopercompany.weetherapplicationinkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public class ApiClint {

    var BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private var retrofit: Retrofit? = null

    public fun getClint(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }


}