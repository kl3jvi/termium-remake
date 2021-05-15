package com.kl3jvi.termium

import android.content.Intent
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, HomeFragment())
            .commitAllowingStateLoss()

        bottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                var selectedFragment: Fragment = HomeFragment();


                when (position) {
                    0 -> selectedFragment = HomeFragment()
                    1 -> selectedFragment = IPGeolocator()
                    2 -> selectedFragment = DNSFragment()
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.root_container, selectedFragment)
                    .commitAllowingStateLoss()
            }
            override fun onTabUnselected(position: Int) {}
            override fun onTabReselected(position: Int) {}
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;

        if (id == R.id.termux) {
            var launchIntent: Intent? = null
            try {
                launchIntent = packageManager.getLaunchIntentForPackage("com.termux")
            } catch (ignored: Exception) {
            }
            if (launchIntent == null) {
                startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "com.termux")))
            } else {
                startActivity(launchIntent)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}