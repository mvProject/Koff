package com.mvproject.koff.data.scorers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ScorersAdapter(var scorers: MutableList<Scorer>) : RecyclerView.Adapter<ScorersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScorersViewHolder {

        return ScorersViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return scorers.size
    }

    override fun onBindViewHolder(holder: ScorersViewHolder, position: Int) {
        holder.bindItem(scorers[position])
    }
}