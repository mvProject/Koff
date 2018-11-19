package com.mvproject.koff.gameSchedule

data class GameWeek (var id : Int,
                     var weekNumber : String,
                     var weekDate : String,
                     var Games : List<Game>
)
