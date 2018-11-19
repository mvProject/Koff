package com.mvproject.koff

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.misc.*
import kotlinx.android.synthetic.main.fragment_scorers.*
import com.mvproject.koff.scorers.Scorer
import com.mvproject.koff.scorers.getAllScorers
import com.mvproject.koff.scorers.getScorersData
import org.jsoup.nodes.Document

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
        writeLog("ScorersFragment->onViewCreated: league select number - " + LEAGUE_SELECTED_NUMBER.toString())
        scorersList.layoutManager = LinearLayoutManager(context)
        loadData(modeSelect(LEAGUE_SELECTED_NUMBER))
    }

    class ScorersViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
        private val playerName = view?.findViewById(R.id.playerName) as TextView
        private val playerTeam = view?.findViewById(R.id.playerTeam) as TextView
        private val playerGoals = view?.findViewById(R.id.playerGoals) as TextView

        fun bindNote(scorer: Scorer) {
            playerName.text = scorer.player
            playerTeam.text = scorer.team
            playerGoals.text = scorer.goals
        }
    }
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

    private fun loadData(league : Document?){
        scorers.clear()
        if (league != null) {
            scorers = league.getScorersData().getAllScorers()
            scorersList.adapter = ScorersAdapter(scorers, context!!)
            writeLog("ScorersFragment->loadData: league table loaded")
        }
    }
}
