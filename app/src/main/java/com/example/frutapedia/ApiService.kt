package com.example.frutapedia
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Url

interface ApiService {
        @GET
        suspend fun getListadoFrutas(@Url url: String): Response<MutableList<FruitsApi>>
    }