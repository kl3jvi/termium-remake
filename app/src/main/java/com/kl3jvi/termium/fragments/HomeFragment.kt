package com.kl3jvi.termium.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.termium.R
import com.kl3jvi.termium.data.ToolItem
import com.kl3jvi.termium.data.ToolsAdapter

class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.home_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val list = ArrayList<ToolItem>()
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

        recyclerView.adapter = context?.let { ToolsAdapter(list, it) }
        recyclerView.layoutManager = LinearLayoutManager(context)
        (recyclerView.layoutManager as LinearLayoutManager?)!!.stackFromEnd = true
        recyclerView.setHasFixedSize(true)
        recyclerView.scrollToPosition(0)

        return view


    }



    class getTools() : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            // ...
            return null
        }
    }



}