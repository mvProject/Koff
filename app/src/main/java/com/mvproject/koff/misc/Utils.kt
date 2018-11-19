package com.mvproject.koff.misc

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

var TABLE_COUNT = 5
var LEAGUE_GAMES_COUNT = 6
val TABLE_RANK = 0
val TABLE_SCORERS = 1
var TABLE_SCHEDULE = 3
val TABLE_DISQ_PLAYERS = 2
val TABLE_DISQ_ADMIN = 3

val PLAYER_TYPE_PLAYER = "гравець"
val PLAYER_TYPE_ADMIN = "представник"

var LEAGUE_SELECTED_NUMBER = 0

var extraLeague : Document? = null
var firstLeague : Document? = null
var secondLeague : Document? = null
var thirdLeague : Document? = null


fun loadKoffData(){
    extraLeague = getDocumentFromUrl(EXTRA_LEAGUE)
    writeLog("extraLeague loaded from loadDataKoff")
    firstLeague = getDocumentFromUrl(FIRST_LEAGUE)
    writeLog("firstLeague loaded from loadDataKoff")
    secondLeague = getDocumentFromUrl(SECOND_LEAGUE)
    writeLog("secondLeague loaded from loadDataKoff")
    thirdLeague = getDocumentFromUrl(THIRD_LEAGUE)
    writeLog("thirdLeague loaded from loadDataKoff")
}

fun getDocumentFromUrl(urls : String): Document {
    val conn = Jsoup.connect(urls).timeout(10000)
    writeLog("StatusCode - " + conn.response().statusCode().toString())
    return conn.get()
}

fun Document.getTablesCount(): Int = this.select("table").size

fun modeSelect(number : Int) : Document? =
    when(number){
        1->firstLeague
        2->secondLeague
        3-> thirdLeague
        else->extraLeague
    }
fun setGamesCount(number : Int) = when(number){
        0-> 6
        else-> 5
    }

fun isConnected(ctx : Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun String.getTeamHomeGoals() : String{
    return try{
        val goals = this.split(":")
        goals[0]
    }
    catch(e: Exception){
        this
    }
}
fun String.getTeamAwayGoals() : String{
    return try{
        val goals = this.split(":")
        goals[1]
    }
    catch(e: Exception){
        this
    }
}

fun writeLog(msg : String){
    Log.d("Koff",msg)
}
