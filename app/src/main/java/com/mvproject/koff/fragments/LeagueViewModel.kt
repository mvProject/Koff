package com.mvproject.koff.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appizona.yehiahd.fastsave.FastSave
import com.google.gson.Gson
import com.mvproject.koff.data.LeagueData
import com.mvproject.koff.data.teamRank.TeamStat
import com.mvproject.koff.misc.LEAGUE_SELECTED_NUMBER
import com.mvproject.koff.misc.leagues
import com.mvproject.koff.misc.writeLog

class LeagueViewModel : ViewModel() {

    var teamStats = MutableLiveData<MutableList<TeamStat>>()

    private val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName
    // try to load data from prefs
    fun load() {
        if (FastSave.getInstance().isKeyExists(key)) {
            writeLog("league key $key found")
            // get league teams data from prefs
            val data = FastSave.getInstance().getObject(key, LeagueData::class.java)
            // cast json to teams list
            teamStats.value =
                Gson().fromJson(data.leagueTable, Array<TeamStat>::class.java).toMutableList()

        }
    }
}
