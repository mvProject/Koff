package com.mvproject.koff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import android.widget.TextView
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import com.mvproject.koff.gameSchedule.GameWeek
import com.mvproject.koff.gameSchedule.getAllGamesShedule
import com.mvproject.koff.gameSchedule.getGameSheduleData
import com.mvproject.koff.misc.*
import org.jsoup.nodes.Document

/**
 * A simple [Fragment] subclass.
 *
 */
class ScheduleFragment : Fragment() {
    private var gameWeeks = mutableListOf<GameWeek>()
    val expMgr = RecyclerViewExpandableItemManager(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        writeLog("Schedule->onViewCreated: league select number - " + LEAGUE_SELECTED_NUMBER.toString())
        scheduleList.layoutManager = LinearLayoutManager(context)
        loadData(modeSelect(LEAGUE_SELECTED_NUMBER))
    }

    internal class MyGroupViewHolder(itemView: View) : AbstractExpandableItemViewHolder(itemView) {
        val gameWeekNumber = itemView.findViewById(R.id.gameWeekNumber) as TextView
        val gameDate = itemView.findViewById(R.id.gameDate) as TextView
    }

    internal class MyChildViewHolder(itemView: View) : AbstractExpandableItemViewHolder(itemView) {
        val gameTime = itemView.findViewById(R.id.gameTime) as TextView
        val teamHome = itemView.findViewById(R.id.teamHome) as TextView
        val teamAway = itemView.findViewById(R.id.teamAway) as TextView
        val goalsHome = itemView.findViewById(R.id.goalsHome) as TextView
        val goalsAway = itemView.findViewById(R.id.goalsAway) as TextView
    }

    internal class MyAdapter(gameWeeks: MutableList<GameWeek>) : AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder>() {
        //var mItems: MutableList<MyGroupItem>

        var mItems: MutableList<GameWeek>
        init {
            setHasStableIds(true) // this is required for expandable feature.
            mItems = gameWeeks
        }

        override fun getGroupCount(): Int {
            return mItems.size
        }

        override fun getChildCount(groupPosition: Int): Int {
            return mItems[groupPosition].Games.size
        }

        override fun getGroupId(groupPosition: Int): Long {
            // This method need to return unique value within all group items.
            return mItems[groupPosition].id.toLong()
        }

        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            // This method need to return unique value within the group.
            return mItems[groupPosition].Games[childPosition].id.toLong()
        }

        override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): MyGroupViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_header_item, parent, false)
            return MyGroupViewHolder(v)
        }

        override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): MyChildViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_games_item, parent, false)
            return MyChildViewHolder(v)
        }

        override fun onBindGroupViewHolder(holder: MyGroupViewHolder, groupPosition: Int, viewType: Int) {
            val group = mItems[groupPosition]
            holder.apply {
                gameWeekNumber.text = group.weekNumber
                gameDate.text = group.weekDate
            }
        }

        override fun onBindChildViewHolder(holder: MyChildViewHolder,groupPosition: Int,childPosition: Int,viewType: Int) {
            val child = mItems[groupPosition].Games[childPosition]
            holder.apply{
                gameTime.text = child.gameTime
                teamHome.text = child.teamHome
                teamAway.text = child.teamAway
                goalsHome.text = child.gameScore.getTeamHomeGoals()
                goalsAway.text = child.gameScore.getTeamAwayGoals()
            }
        }

        override fun onCheckCanExpandOrCollapseGroup(holder: MyGroupViewHolder,groupPosition: Int,x: Int,y: Int,expand: Boolean): Boolean {
            return true
        }
    }

    private fun loadData(league : Document?){
        gameWeeks.clear()
        (scheduleList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        if (league != null) {
            TABLE_SCHEDULE = league.getTablesCount() - 2
            writeLog("ScheduleFragment->loadData: snedule table number - " + TABLE_SCHEDULE)
            LEAGUE_GAMES_COUNT = setGamesCount(LEAGUE_SELECTED_NUMBER)
            writeLog("ScheduleFragment->loadData: games count - " + LEAGUE_GAMES_COUNT)
            gameWeeks = league.getGameSheduleData().getAllGamesShedule()
            writeLog("gameWeeks - " + gameWeeks.size)
            scheduleList.adapter = expMgr.createWrappedAdapter(MyAdapter(gameWeeks))
            expMgr.attachRecyclerView(scheduleList)
            writeLog("ScheduleFragment->loadData: league table loaded")
        }
    }

}
