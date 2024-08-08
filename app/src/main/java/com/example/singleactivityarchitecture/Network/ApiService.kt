package com.example.singleactivityarchitecture.Network

import com.example.singleactivityarchitecture.dataBase.table.Poster
import retrofit2.http.GET

interface ApiService {

    @GET("DisneyPosters2.json")
    suspend fun fetchDisneyPosterList(): List<Poster>
}