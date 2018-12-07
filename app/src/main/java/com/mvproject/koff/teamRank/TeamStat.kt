package com.mvproject.koff.teamRank

import com.google.gson.annotations.SerializedName

data class TeamStat (@SerializedName("teamRank") var teamRank : String,
                     @SerializedName("teamName") var teamName : String,
                     @SerializedName("gamesPlayed") var gamesPlayed : String,
                     @SerializedName("gamesWin") var gamesWin : String,
                     @SerializedName("gamesDraw") var gamesDraw : String,
                     @SerializedName("gamesLost") var gamesLost : String,
                     @SerializedName("goalsOut") var goalsOut : String,
                     @SerializedName("goalsIn") var goalsIn : String,
                     @SerializedName("goalsDiff") var goalsDiff : String,
                     @SerializedName("teamPoints") var teamPoints : String
)
{
    fun statToString():String = "$gamesPlayed   $gamesWin-$gamesDraw-$gamesLost   $goalsOut-$goalsIn $goalsDiff"
}