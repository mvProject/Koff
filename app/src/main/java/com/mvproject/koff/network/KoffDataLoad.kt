package com.mvproject.koff.network

import com.appizona.yehiahd.fastsave.FastSave
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvproject.koff.data.LeagueData
import com.mvproject.koff.data.Leagues
import com.mvproject.koff.misc.JsonOut
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class KoffDataLoad {

    private val api = initApi()
    /**
     * Achieving selected league data
     * saving in prefs
     */
    suspend fun getLeagueData(league : Leagues) {
        val table = api.getLeagueTable(league.leagueUrl).await()
        val scorers = api.getLeagueScorers(league.leagueUrl).await()
        val calendar = api.getLeagueSchedule(league.leagueUrl).await()
        if (table.isNotEmpty() and scorers.isNotEmpty() and calendar.isNotEmpty())
        {
            val data =
                LeagueData(
                    league.leagueName,
                    table.JsonOut(),
                    scorers.JsonOut(),
                    calendar.JsonOut()
                )
            FastSave.getInstance().saveObject(league.leagueName,data)
        }
    }

    /**r
     * Retrofit Initializationr
     */
    private fun initApi() : KoffApi {

        val gson = GsonConverterFactory.create()

        val apiClient = OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().apply {
            baseUrl(KoffApi.BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(gson)
            client(apiClient)
        }.build().create(KoffApi::class.java)
    }
}