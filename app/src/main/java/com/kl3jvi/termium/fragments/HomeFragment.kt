package com.kl3jvi.termium.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.termium.R
import com.kl3jvi.termium.data.ToolItem
import com.kl3jvi.termium.data.ToolsAdapter
import com.yarolegovich.lovelydialog.LovelyStandardDialog

class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<ToolItem>;
    var adapter: ToolsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.home_fragment, container, false)
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView)
        list = ArrayList()

        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Social Fish",
                "An advanced admin panel finder written in python."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Admin Panel Finder",
                "An advanced admin panel finder written in python."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Androbug Framework",
                "Gather Information about hidden files on a web server."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Angry Fuzzer",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "A-Rat",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Black Eye",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Brutal",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "BruteSpray",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "ChoiceBot",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Click Jacking Scanner",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "CMS Map",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Cred Map",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Crips IP Tools",
                "This tool is the first added to this holy glorious app."
            )
        )
        list.add(
            ToolItem(
                "https://eu.ui-avatars.com/api/?rounded=true&background=random&name=",
                "Second Tool",
                "This tool is the first added to this holy glorious app."
            )
        )
        adapter = context?.let { ToolsAdapter(list, it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        (recyclerView.layoutManager as LinearLayoutManager?)!!.stackFromEnd = false
        recyclerView.setHasFixedSize(true)
        recyclerView.scrollToPosition(0)



        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.action_one);
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: " + query)
                adapter?.filter?.filter(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
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
                        launchIntent =
                            activity?.packageManager?.getLaunchIntentForPackage("com.termux")
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