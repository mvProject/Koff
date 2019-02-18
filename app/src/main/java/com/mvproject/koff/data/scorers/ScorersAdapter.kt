package com.mvproject.koff.data.scorers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R

class ScorersAdapter(var scorers: MutableList<Scorer>, var context: Context) : RecyclerView.Adapter<ScorersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScorersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scorer_item_list, parent, false)
        return ScorersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scorers.size
    }

    override fun onBindViewHolder(holder: ScorersViewHolder, position: Int) {
        holder.bindNote(scorers[position])
    }
}