package com.mvproject.koff.data.scorers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R
import com.mvproject.koff.databinding.ScorerItemListBinding

/*class ScorersViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
    private val playerName = view?.findViewById(R.id.playerName) as TextView
    private val playerTeam = view?.findViewById(R.id.playerTeam) as TextView
    private val playerGoals = view?.findViewById(R.id.playerGoals) as TextView

    fun bindNote(scorer: Scorer) {
        playerName.text = scorer.player
        playerTeam.text = scorer.team
        playerGoals.text = scorer.goals
    }
}

 */
class ScorersViewHolder(
    private val parent: ViewGroup,
    private val binding: ScorerItemListBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.scorer_item_list,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(team: Scorer) {
        binding.scorerModel = team

    }
}
