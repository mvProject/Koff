package com.mvproject.koff.data.gameSchedule

import com.mvproject.koff.misc.getTeamAwayGoals
import com.mvproject.koff.misc.getTeamHomeGoals

data class Game (var id : Int,
                  var gameTime : String,
                  var teamHome : String,
                  var gameScore : String,
                  var teamAway : String
)
{
    fun getHomeGoals():String = gameScore.getTeamHomeGoals()

    fun getAwayGoals():String = gameScore.getTeamAwayGoals()

}