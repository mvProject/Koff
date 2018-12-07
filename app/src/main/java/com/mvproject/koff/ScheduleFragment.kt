package com.mvproject.koff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import com.appizona.yehiahd.fastsave.FastSave
import com.google.gson.Gson
import com.mvproject.koff.gameSchedule.GameWeek
import com.mvproject.koff.misc.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ScheduleFragment : Fragment() {
    private var gameWeeks = mutableListOf<GameWeek>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName

        if (FastSave.getInstance().isKeyExists(key)){
            writeLog("schedule key $key found")
            scheduleList.layoutManager = LinearLayoutManager(context)
            val data = FastSave.getInstance().getObject(key,LeagueData::class.java)
            gameWeeks = Gson().fromJson(data.leagueSchedule,Array<GameWeek>::class.java).toMutableList()
            val sections = ArrayList<GameWeek>()
            gameWeeks.forEach {
                sections.add(GameWeek(0,it.weekNumber,it.weekDate,it.Games))
            }
            val adapterRecycler = AdapterSectionRecycler(this.context!!, sections)
            scheduleList.adapter = adapterRecycler
        }
        else{
            writeLog("schedule key $key not found")
        }
    }
}
