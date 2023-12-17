package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class EditInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        val saveInformationBtn = findViewById<Button>(R.id.save_information)
        saveInformationBtn.setOnClickListener{
            val saveInformationBtn = Intent(this, ProfilePage::class.java)
            startActivity(saveInformationBtn)
        }
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            val popBtn = Intent(this, ProfilePage::class.java)
            startActivity(popBtn)
        }
    }
}