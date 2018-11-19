package com.mvproject.koff.teamRank

data class TeamStat (var teamRank : String,
                     var teamName : String,
                     var gamesPlayed : String,
                     var gamesWin : String,
                     var gamesDraw : String,
                     var gamesLost : String,
                     var goalsOut : String,
                     var goalsIn : String,
                     var goalsDiff : String,
                     var teamPoints : String
)
{
    fun statToString():String = "$gamesPlayed   $gamesWin-$gamesDraw-$gamesLost   $goalsOut-$goalsIn $goalsDiff"
}