package com.kl3jvi.termium

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kl3jvi.termium.fragments.DNSFragment
import com.kl3jvi.termium.fragments.HomeFragment
import com.kl3jvi.termium.fragments.IPGeolocator
import com.kl3jvi.termium.fragments.LinuxCommands
import com.yarolegovich.lovelydialog.LovelyStandardDialog

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationBar
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        bottomNavBar = findViewById(R.id.bottom_navigation_bar)
        bottomNavBar.addItem(BottomNavigationItem(R.drawable.ic_round_home_24, "Home"))
            .addItem(BottomNavigationItem(R.drawable.ic_ip, "IP Geolocation"))
            .addItem(BottomNavigationItem(R.drawable.ic_dns, "DNS Lookup"))
            .addItem(BottomNavigationItem(R.drawable.ic_tux, "Linux Commands"))
            .setFirstSelectedPosition(0)
            .setMode(BottomNavigationBar.MODE_SHIFTING)
            .setActiveColor(R.color.purple_500)
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
            .initialise()

        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, HomeFragment())
            .commitAllowingStateLoss()

        bottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                var selectedFragment: Fragment = HomeFragment()

                when (position) {
                    0 -> selectedFragment = HomeFragment()
                    1 -> selectedFragment = IPGeolocator()
                    2 -> selectedFragment = DNSFragment()
                    3 -> selectedFragment = LinuxCommands()
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_container, selectedFragment)
                    .commitAllowingStateLoss()
            }
            override fun onTabUnselected(position: Int) {}
            override fun onTabReselected(position: Int) {}
        })


    }





}