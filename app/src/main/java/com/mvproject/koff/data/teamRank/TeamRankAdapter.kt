package com.mvproject.koff.data.teamRank

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TeamRankAdapter(var teamRanks: MutableList<TeamStat>) : RecyclerView.Adapter<TeamRankViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRankViewHolder {
        return TeamRankViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return teamRanks.size
    }

    override fun onBindViewHolder(holder: TeamRankViewHolder, position: Int) {
        holder.bindItem(teamRanks[position])
    }
}