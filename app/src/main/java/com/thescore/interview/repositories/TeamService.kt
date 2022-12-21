package com.thescore.interview.repositories

import com.thescore.interview.data.Team
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface TeamService {
    @GET("input.json")
    suspend fun getTeams(): List<Team>
}

val teamService = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/")
    .build()
    .create<TeamService>()