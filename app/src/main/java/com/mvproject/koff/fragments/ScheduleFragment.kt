package com.mvproject.koff.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_schedule.*
import com.appizona.yehiahd.fastsave.FastSave
import com.google.gson.Gson
import com.jaychang.srv.decoration.SimpleSectionHeaderProvider
import com.mvproject.koff.data.LeagueData
import com.mvproject.koff.R
import com.mvproject.koff.data.gameSchedule.GameWeek
import com.mvproject.koff.misc.*
import com.mvproject.koff.data.gameSchedule.Category
import com.mvproject.koff.data.gameSchedule.GameHolder
import com.mvproject.koff.data.gameSchedule.GameItem

/**
 * A schedule [Fragment] subclass.
 *
 */
class ScheduleFragment : Fragment() {
    private var gameWeeks = mutableListOf<GameWeek>()

  //  lateinit var adapter: RecyclerViewAdapter
  //  private var layoutManager: LinearLayoutManager? = null
  //  private var kmHeaderItemDecoration: KmHeaderItemDecoration? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      initGamesList()
      /*
        // name of selected league
        val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName
        // try to load data from prefs
        if (FastSave.getInstance().isKeyExists(key)){
            writeLog("schedule key $key found")

            // get schedule data from prefs
            val data = FastSave.getInstance().getObject(key,LeagueData::class.java)
            // cast json to gameweeks list
            gameWeeks = Gson().fromJson(data.leagueSchedule,Array<GameWeek>::class.java).toMutableList()
            //create headers list
            val sections = ArrayList<GameWeek>()
            gameWeeks.forEach {
                 //add gameweek info and list of games to headers
                sections.add(GameWeek(0,it.weekNumber,it.weekDate,it.Games))
            }
            val adapterRecycler = AdapterSectionRecycler(this.context!!, sections)
            // Add the sticky headers decoration

            scheduleList.layoutManager = LinearLayoutManager(context)
            scheduleList.adapter = adapterRecycler
        }
        else{
            writeLog("schedule key $key not found")
        }
        */
    }


    private fun initGamesList(){
        scheduleList.setSectionHeader(getGamesHeader())
        scheduleList.addCells(getGamesData())
    }

    private fun getGamesHeader() : SimpleSectionHeaderProvider<GameItem> {
        return object : SimpleSectionHeaderProvider<GameItem>() {
            @SuppressLint("InflateParams")
            override fun getSectionHeaderView(Game: GameItem, i: Int): View {
                val view = LayoutInflater.from(context).inflate(R.layout.schedule_header_item, null, false)
                val gameWeekNumber = view.findViewById(R.id.gameWeekNumber)as TextView
                val gameDate = view.findViewById(R.id.gameDate) as TextView
                gameWeekNumber.text = Game.weekNumber
                gameDate.text = Game.weekDate
                return view
            }

            override fun isSameSection(Game: GameItem, nextGame: GameItem): Boolean {
                return Game.categoryId == nextGame.categoryId
            }

            // Optional, whether the header is sticky, default false
            override fun isSticky(): Boolean {
                return true
            }
        }
    }

    private fun getGamesData() : ArrayList<GameHolder>{
        val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName
        val cells = ArrayList<GameHolder>()

        if (FastSave.getInstance().isKeyExists(key)){
            writeLog("schedule key $key found")
            // get schedule data from prefs
            val data = FastSave.getInstance().getObject(key, LeagueData::class.java)
            // cast json to gameweeks list
            gameWeeks = Gson().fromJson(data.leagueSchedule,Array<GameWeek>::class.java).toMutableList()

            val games = ArrayList<GameItem>()
            var week: Category
            var id = 0
            gameWeeks.forEach {
                week = Category(id, it.weekNumber, it.weekDate)
                for (game in it.Games){
                    games.add(
                        GameItem(
                            0,
                            game.gameTime,
                            game.teamHome,
                            game.gameScore,
                            game.teamAway,
                            week
                        )
                    )
                }
                id++
            }

            for (game in games) {
                cells.add(GameHolder(game))
            }
        }
       // val Games = data2
        return cells
    }
}
