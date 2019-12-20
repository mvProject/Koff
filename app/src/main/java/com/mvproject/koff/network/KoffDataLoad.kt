package com.mvproject.koff.network

import com.appizona.yehiahd.fastsave.FastSave
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvproject.koff.data.LeagueData
import com.mvproject.koff.data.Leagues
import com.mvproject.koff.misc.JsonOut
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val table = api.getLeagueTableAsync(league.leagueUrl).await()
        val scorers = api.getLeagueScorersAsync(league.leagueUrl).await()
        val calendar = api.getLeagueScheduleAsync(league.leagueUrl).await()
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

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val apiClient = OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder().apply {
            baseUrl(KoffApi.BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())
            client(apiClient)
        }.build().create(KoffApi::class.java)
    }
}