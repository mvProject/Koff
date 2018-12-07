package com.mvproject.koff

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View


class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //init {
        val gameTime = itemView.findViewById(R.id.gameTime) as TextView
        val teamHome = itemView.findViewById(R.id.teamHome) as TextView
        val teamAway = itemView.findViewById(R.id.teamAway) as TextView
        val goalsHome = itemView.findViewById(R.id.goalsHome) as TextView
        val goalsAway = itemView.findViewById(R.id.goalsAway) as TextView
   //

}