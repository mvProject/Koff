package com.mvproject.koff.scorers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R

class ScorersViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
    private val playerName = view?.findViewById(R.id.playerName) as TextView
    private val playerTeam = view?.findViewById(R.id.playerTeam) as TextView
    private val playerGoals = view?.findViewById(R.id.playerGoals) as TextView

    fun bindNote(scorer: Scorer) {
        playerName.text = scorer.player
        playerTeam.text = scorer.team
        playerGoals.text = scorer.goals
    }
}