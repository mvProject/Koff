package com.mvproject.koff.teamRank

import com.mvproject.koff.misc.TABLE_RANK
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun Document.getTeamRankData() : Elements = this.select("table")[TABLE_RANK].select("tr")

fun Elements.getAllTeamRanks() : MutableList<TeamStat> {
    val list = mutableListOf<TeamStat>()
    for (i in 2 until this.size) {
        val row = this[i].select("td")
        val pl = TeamStat(
            row[0].text(),
            row[1].text(),
            row[2].text(),
            row[3].text(),
            row[4].text(),
            row[5].text(),
            row[6].text(),
            row[7].text(),
            row[8].text(),
            row[9].text()
        )
        list.add(pl)
    }
    return list
}
