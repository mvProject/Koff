package com.mvproject.koff.gameSchedule

import com.mvproject.koff.misc.LEAGUE_GAMES_COUNT
import com.mvproject.koff.misc.TABLE_SCHEDULE
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun Document.getGameSheduleData() : Elements = this.select("table")[TABLE_SCHEDULE].select("tr")

fun Elements.getAllGamesShedule() : MutableList<GameWeek> {
     val list = mutableListOf<GameWeek>()
        var counter = 0
        for (i in 2 until this.size step LEAGUE_GAMES_COUNT+1) {
            list.add(this.getGameWeek(i,counter))
            counter++
        }
     return list
}

private fun Elements.getGameWeek(number : Int,id : Int) : GameWeek {
    val games = mutableListOf<Game>()
    val weekNumberRow = this[number].select("td")
    val gameDateRow = this[number+1].select("td")
    for (i in number+1 until number+LEAGUE_GAMES_COUNT+1) {
        val gameRow = this[i].select("td")
        val game = Game(i, gameRow[2].text(), gameRow[3].text(), gameRow[4].text(), gameRow[5].text())
        games.add(game)
    }
    return GameWeek(id,weekNumberRow[0].text(), gameDateRow[0].text(),games)
}


