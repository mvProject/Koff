package com.mvproject.koff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.jaredrummler.materialspinner.MaterialSpinner
import com.mvproject.koff.misc.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        initNavigation()

        if(isConnected(applicationContext)){
            loadData()
        }
        initSpinner()
    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
        writeLog("navigation init")
    }

    private fun initSpinner(){
        val list = resources.getStringArray(R.array.league_array).toList()
        val spinner = findViewById<MaterialSpinner>(R.id.league_spinner)
        spinner.setItems(list)
        spinner.setOnItemSelectedListener { view, position, id, item ->
            val selectedMenu = navController.currentDestination!!.id
            LEAGUE_SELECTED_NUMBER = position
            writeLog("league selected - " + LEAGUE_SELECTED_NUMBER.toString())
            navController.navigate(selectedMenu)
            writeLog("league selected - " + LEAGUE_SELECTED_NUMBER.toString())
        }
        writeLog("spinner init")
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    private fun loadData(){
        val dialog = this.indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")
        dialog.show()
        doAsync {
            //extraLeague = getDocumentFromUrl(modeSelect(LEAGUE_SELECTED_NUMBER))
            loadKoffData()
            uiThread {
                dialog.dismiss()
                writeLog("loadKoffData loading ended")
                navController.navigate(R.id.leagueFragment)
            }
        }
    }
}
