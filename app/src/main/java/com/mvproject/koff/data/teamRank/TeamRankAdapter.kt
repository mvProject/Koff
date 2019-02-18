package com.mvproject.koff.data.teamRank

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R

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