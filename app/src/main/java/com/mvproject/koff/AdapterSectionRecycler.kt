package com.mvproject.koff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import com.mvproject.koff.gameSchedule.Game
import com.mvproject.koff.gameSchedule.GameWeek
import com.mvproject.koff.misc.getTeamAwayGoals
import com.mvproject.koff.misc.getTeamHomeGoals


class AdapterSectionRecycler(internal var context: Context, sectionItemList: List<GameWeek>) :
    SectionRecyclerViewAdapter<GameWeek, Game, GameSectionViewHolder, GamesViewHolder>(context, sectionItemList) {

    override fun onCreateSectionViewHolder(sectionViewGroup: ViewGroup, viewType: Int): GameSectionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_header_item, sectionViewGroup, false)
        return GameSectionViewHolder(view)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_games_item, childViewGroup, false)
        return GamesViewHolder(view)
    }

    override fun onBindSectionViewHolder(
        sectionViewHolder: GameSectionViewHolder,
        sectionPosition: Int,
        section: GameWeek
    ) {
        sectionViewHolder.gameWeekNumber.text = section.weekNumber
        sectionViewHolder.gameDate.text = section.weekDate
    }

    override fun onBindChildViewHolder(
        childViewHolder: GamesViewHolder,
        sectionPosition: Int,
        childPosition: Int,
        child: Game
    ) {
        childViewHolder.gameTime.text = child.gameTime
        childViewHolder.teamHome.text = child.teamHome
        childViewHolder.teamAway.text = child.teamAway
        childViewHolder.goalsHome.text = child.gameScore.getTeamHomeGoals()
        childViewHolder.goalsAway.text = child.gameScore.getTeamAwayGoals()
    }
}