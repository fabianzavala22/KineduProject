package com.example.kineduproject.webservice

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

     const val BASE_URL = "https://staging.kinedu.com/api/v3/"

    val webService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(WebService::class.java)
        }


}