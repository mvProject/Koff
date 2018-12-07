package com.mvproject.koff

import com.appizona.yehiahd.fastsave.FastSave
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvproject.koff.misc.JsonOut
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KoffDataLoad {

    private val api = initApi()

    suspend fun getLeagueData(league : Leagues) {
        val table = api.getLeagueTable(league.leagueUrl).await()
        val scorers = api.getLeagueScorers(league.leagueUrl).await()
        val calendar = api.getLeagueSchedule(league.leagueUrl).await()
        val data = LeagueData(league.leagueName,table.JsonOut(),scorers.JsonOut(),calendar.JsonOut())
        FastSave.getInstance().saveObject(league.leagueName,data)
    }


    private fun initApi() : KoffApi {

        val gson = GsonConverterFactory.create()

        val apiClient = OkHttpClient.Builder().build()

        return Retrofit.Builder().apply {
            baseUrl(KoffApi.BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(gson)
            client(apiClient)
        }.build().create(KoffApi::class.java)
    }
}