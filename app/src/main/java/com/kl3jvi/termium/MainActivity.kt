package com.kl3jvi.termium

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kl3jvi.termium.data.ToolItem
import com.kl3jvi.termium.data.ToolsAdapter
import com.kl3jvi.termium.fragments.DNSFragment
import com.kl3jvi.termium.fragments.HomeFragment
import com.kl3jvi.termium.fragments.IPGeolocator
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
        val id = item.itemId
        val dialog = LovelyStandardDialog(context)

        if (id == R.id.termux) {
            dialog.setTopColor(Color.parseColor("#044e97"))
                .setIcon(R.drawable.ic_termux)
                .setTitle("Do you want to go to Termux?")
                .setMessage("Go to termux and paste the commands to try the tools.")
                .setPositiveButton("Yes") {
                    var launchIntent: Intent? = null
                    try {
                        launchIntent = packageManager.getLaunchIntentForPackage("com.termux")
                    } catch (ignored: Exception) {
                        println(ignored.message)
                    }
                    if (launchIntent == null) {
                        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "com.termux")))
                    } else {
                        startActivity(launchIntent)
                    }
                }.setNegativeButton("No", null)
                .setButtonsColor(Color.parseColor("#044e97"))
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}