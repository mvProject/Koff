package com.mvproject.koff.misc

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.Gson
import com.mvproject.koff.data.Leagues

var LEAGUE_SELECTED_NUMBER = 0

val leagues = mutableListOf<Leagues>()

/**
 * Init available leagues
 */
fun initLeagues() {
    leagues.add(Leagues("Футзал Экстра Лига", "1246214_11"))
    leagues.add(Leagues("Футзал Первая Лига", "1246216_11"))
    leagues.add(Leagues("Футзал Вторая Лига", "1246218_11"))
    leagues.add(Leagues("Футзал Третья Лига", "1246220_11"))
}
/**
 * Cast list to json
 */
fun MutableList<*>.JsonOut() : String{
    return Gson().toJson(this)
}
/**
 * Check internet connection
 */
fun isConnected(ctx : Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}
/**
 * Extract home team goals from Score string
 */
fun String.getTeamHomeGoals() : String{
    return try{
        val goals = this.split(":")
        goals[0]
    }
    catch(e: Exception){
        this
    }
}
/**
 * Extract away team goals from Score string
 */
fun String.getTeamAwayGoals() : String{
    return try{
        val goals = this.split(":")
        goals[1]
    }
    catch(e: Exception){
        this
    }
}
/**
 * Simplifying writing debug logs
 */
fun writeLog(msg : String){
    Log.d("Koff",msg)
}


