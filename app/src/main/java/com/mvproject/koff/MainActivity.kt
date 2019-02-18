package com.mvproject.koff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.appizona.yehiahd.fastsave.FastSave
import kotlinx.android.synthetic.main.activity_main.*
import com.jaredrummler.materialspinner.MaterialSpinner
import com.mvproject.koff.misc.*
import com.mvproject.koff.network.KoffDataLoad
import kotlinx.coroutines.*
import org.jetbrains.anko.indeterminateProgressDialog

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var myJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        FastSave.init(applicationContext)

        initLeagues()

        initNavigation()

        initSpinner()
    }

    /**
     * Refresh Menu Creating
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    /**
     * Get data on refresh click
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.data_refresh -> {
                if(isConnected(applicationContext)){
                    getData()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Init Bottom Navigation
     */
    private fun initNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
    /**
     * Create spinner and fill with league names
     */
    private fun initSpinner(){
        val spinner = findViewById<MaterialSpinner>(R.id.league_spinner)
        spinner.setItems(leagues.map { it.leagueName })
        spinner.setOnItemSelectedListener { view, position, id, item ->
            reNavigate(position)
        }
    }
    /**
     * Navigate to same menu item
     * and to same league
     */
    private fun reNavigate(position: Int) {
        val selectedMenu = navController.currentDestination!!.id
        LEAGUE_SELECTED_NUMBER = position
        navController.navigate(selectedMenu)
    }
    /**
     * Navigate Menu Up Creating
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
    /**
     * Fetch data from internet and backup in prefs
     */
    private fun getData(){
        val dialog = this.indeterminateProgressDialog(message = "Please wait a bit…")
        dialog.show()
        myJob = CoroutineScope(Dispatchers.IO).launch {
            leagues.forEach {
                KoffDataLoad().getLeagueData(it)
            }
            withContext(Dispatchers.Main) {
                dialog.dismiss()
                reNavigate(LEAGUE_SELECTED_NUMBER)
            }
        }
    }


}
