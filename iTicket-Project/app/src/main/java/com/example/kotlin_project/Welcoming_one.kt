package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class

class Welcoming_one : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcoming_one)

        // Fix the typo in the button variable name
        val learnMoreBtn = findViewById<Button>(R.id.learn_more_btn)

        // Fix the typo in the intent variable name
        learnMoreBtn.setOnClickListener{
            val learnMoreIntent = Intent(this, Log_in::class.java)
            startActivity(learnMoreIntent)
        }
    }
}
