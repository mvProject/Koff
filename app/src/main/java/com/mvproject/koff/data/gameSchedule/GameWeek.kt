package com.mvproject.koff.data.gameSchedule

data class GameWeek (var id : Int,
                      var weekNumber : String,
                      var weekDate : String,
                      var Games : MutableList<Game>
)

