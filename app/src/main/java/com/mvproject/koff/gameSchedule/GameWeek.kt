package com.mvproject.koff.gameSchedule

import com.intrusoft.sectionedrecyclerview.Section

data class GameWeek (var id : Int,
                     var weekNumber : String,
                     var weekDate : String,
                     var Games : MutableList<Game>
)
    : Section<Game> {
    override fun getChildItems(): MutableList<Game> {
        return Games
   }
}
