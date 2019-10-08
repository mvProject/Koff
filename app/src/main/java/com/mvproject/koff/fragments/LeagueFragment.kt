package com.mvproject.koff.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appizona.yehiahd.fastsave.FastSave
import com.google.gson.Gson
import com.mvproject.koff.data.LeagueData
import com.mvproject.koff.R
import com.mvproject.koff.data.teamRank.TeamRankAdapter
import com.mvproject.koff.data.teamRank.TeamStat
import com.mvproject.koff.misc.*
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A teams table [Fragment] subclass.
 *
 */
class LeagueFragment : Fragment() {

    private var teamStats = mutableListOf<TeamStat>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // name of selected league
        val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName
        // try to load data from prefs
        if (FastSave.getInstance().isKeyExists(key)){
            writeLog("league key $key found")
            // get league teams data from prefs
            val data = FastSave.getInstance().getObject(key, LeagueData::class.java)
            // cast json to teams list
            teamStats = Gson().fromJson(data.leagueTable,Array<TeamStat>::class.java).toMutableList()
            teamRankList.layoutManager = LinearLayoutManager(context)
            teamRankList.adapter = TeamRankAdapter(teamStats, context!!)
        }
        else{
            writeLog("league key $key not found")
        }
    }
}
