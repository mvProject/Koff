package com.mvproject.koff.scorers

import com.mvproject.koff.misc.TABLE_SCORERS
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun Document.getScorersData() : Elements = this.select("table")[TABLE_SCORERS].select("tr")

fun Elements.getAllScorers() : MutableList<Scorer> {
    val list = mutableListOf<Scorer>()
    for (i in 2 until this.size) {
        val row = this[i].select("td")
        val pl = Scorer(row[0].text(), row[1].text(), row[2].text())
        list.add(pl)
    }
    return list
}