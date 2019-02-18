package com.mvproject.koff.data.teamRank

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R

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