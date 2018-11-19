package com.mvproject.koff

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.misc.*
import com.mvproject.koff.teamRank.*
import kotlinx.android.synthetic.main.fragment_league.*
import org.jsoup.nodes.Document

/**
 * A simple [Fragment] subclass.
 *
 */
class LeagueFragment : Fragment() {

    private var teamStats = mutableListOf<TeamStat>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        writeLog("LeagueFragment->onViewCreated: league select number - " + LEAGUE_SELECTED_NUMBER.toString())
        teamRankList.layoutManager = LinearLayoutManager(context)
        loadData(modeSelect(LEAGUE_SELECTED_NUMBER))
    }
    class TeamRankViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
        private val teamRank = view?.findViewById(R.id.teamRank) as TextView
        private val teamName = view?.findViewById(R.id.teamName) as TextView
        private val teamStats = view?.findViewById(R.id.teamStats) as TextView
        private val teamPoints = view?.findViewById(R.id.teamPoints) as TextView

        fun bindNote(teamStat: TeamStat) {
            teamRank.text = teamStat.teamRank
            teamName.text = teamStat.teamName
            teamStats.text = teamStat.statToString()
            teamPoints.text = teamStat.teamPoints
        }
    }
    class TeamRankAdapter(var teamStats: MutableList<TeamStat>, var context: Context) : RecyclerView.Adapter<TeamRankViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRankViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.teamrank_item_list, parent, false)
            return TeamRankViewHolder(view)
        }

        override fun getItemCount(): Int {
            return teamStats.size
        }

        override fun onBindViewHolder(holder: TeamRankViewHolder, position: Int) {
            holder.bindNote(teamStats[position])
        }
    }

    private fun loadData(league : Document?){
        teamStats.clear()
        if (league != null) {
            teamStats = league.getTeamRankData().getAllTeamRanks()
            teamRankList.adapter = TeamRankAdapter(teamStats, context!!)
            writeLog("LeagueFragment->loadData: league table loaded")
        }
    }
}
