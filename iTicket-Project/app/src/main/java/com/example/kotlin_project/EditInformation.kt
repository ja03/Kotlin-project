package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EditInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        val saveInformationBtn = findViewById<Button>(R.id.save_information)
        saveInformationBtn.setOnClickListener{
            val saveInformationBtn = Intent(this, ProfilePage::class.java)
            startActivity(saveInformationBtn)
        }
    }
}