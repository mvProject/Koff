package com.mvproject.koff

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameWeekNumber = itemView.findViewById(R.id.gameWeekNumber) as TextView
    val gameDate = itemView.findViewById(R.id.gameDate) as TextView
}