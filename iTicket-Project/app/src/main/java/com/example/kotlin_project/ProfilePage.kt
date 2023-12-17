package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class
import android.widget.ImageButton

class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        val editProfileBtn  = findViewById<Button>(R.id.edit_information)
        val logoutBtn  = findViewById<Button>(R.id.logout)
        editProfileBtn.setOnClickListener{
            val editProfileBtn = Intent(this, EditInformation::class.java)
            startActivity(editProfileBtn)
        }
        logoutBtn.setOnClickListener{
            val logoutBtn = Intent(this, Log_in::class.java)
            startActivity(logoutBtn)
        }
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            val popBtn = Intent(this, Home_page::class.java)
            startActivity(popBtn)
        }
    }
}