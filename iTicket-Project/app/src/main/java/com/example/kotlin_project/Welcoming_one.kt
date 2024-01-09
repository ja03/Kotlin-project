package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Welcoming_one : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcoming_one)
        val learnMoreBtn = findViewById<Button>(R.id.learn_more_btn)
        learnMoreBtn.setOnClickListener{
            val learnMoreIntent = Intent(this, Log_in::class.java)
            startActivity(learnMoreIntent)
        }
    }
}
