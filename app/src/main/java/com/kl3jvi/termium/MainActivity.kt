package com.kl3jvi.termium

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavBar: BottomNavigationBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavBar = findViewById(R.id.bottom_navigation_bar)
        bottomNavBar.addItem(BottomNavigationItem(R.drawable.ic_round_home_24, "Home"))
            .addItem(BottomNavigationItem(R.drawable.ic_ip, "IP Geolocator"))
            .addItem(BottomNavigationItem(R.drawable.ic_dns, "DNS Lookup"))
            .setFirstSelectedPosition(0)
            .setMode(BottomNavigationBar.MODE_SHIFTING)
            .setActiveColor(R.color.purple_500)
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
            .initialise();



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;
        if (id == R.id.action_one) {
            // TODO: Create new intent for search;
        }
        return super.onOptionsItemSelected(item)

    }
}