package com.mvproject.koff.network

import com.mvproject.koff.data.gameSchedule.GameWeek
import com.mvproject.koff.data.scorers.Scorer
import com.mvproject.koff.data.teamRank.TeamStat
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api interface for api call with retrofit from Koff
 */
interface KoffApi {
    // get league table data
    @GET("table/{league}")
    fun getLeagueTableAsync(
        @Path("league") league : String
    ) : Deferred<MutableList<TeamStat>>
    // get league scorers data
    @GET("scorers/{league}")
    fun getLeagueScorersAsync(
        @Path("league") league : String
    ) : Deferred<MutableList<Scorer>>
    // get league schedule data
    @GET("schedule/{league}")
    fun getLeagueScheduleAsync(
        @Path("league") league : String
    ) : Deferred<MutableList<GameWeek>>

    companion object {
        const val BASE_URL = "http://mvpapi.herokuapp.com/footpass/"
    }
}