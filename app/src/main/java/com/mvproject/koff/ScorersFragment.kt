package com.mvproject.koff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.appizona.yehiahd.fastsave.FastSave
import com.google.gson.Gson
import com.mvproject.koff.misc.*
import kotlinx.android.synthetic.main.fragment_scorers.*
import com.mvproject.koff.scorers.Scorer
import com.mvproject.koff.scorers.ScorersAdapter

/**
 * A simple [Fragment] subclass.
 *
 */
class ScorersFragment : Fragment() {
    private var scorers = mutableListOf<Scorer>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scorers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val key = leagues[LEAGUE_SELECTED_NUMBER].leagueName

        scorersList.layoutManager = LinearLayoutManager(context)
        if (FastSave.getInstance().isKeyExists(key)){
            writeLog("scorers key $key found")
            val data = FastSave.getInstance().getObject(key,LeagueData::class.java)
            scorers = Gson().fromJson(data.leagueScorers,Array<Scorer>::class.java).toMutableList()
            scorersList.layoutManager = LinearLayoutManager(context)
            scorersList.adapter = ScorersAdapter(scorers, context!!)
        }
        else{
            writeLog("scorers key $key not found")
        }
    }
}
