package com.kl3jvi.termium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ToolDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_details)
        this.title = intent.getStringExtra("Title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }
}