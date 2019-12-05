package com.mvproject.koff.data.teamRank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R
import com.mvproject.koff.databinding.TeamrankItemListBinding

class TeamRankViewHolder(private val parent: ViewGroup,private val binding: TeamrankItemListBinding = DataBindingUtil.inflate(
    LayoutInflater.from(parent.context),R.layout.teamrank_item_list,parent,false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(team: TeamStat) {
        binding.teamModel = team

    }


}