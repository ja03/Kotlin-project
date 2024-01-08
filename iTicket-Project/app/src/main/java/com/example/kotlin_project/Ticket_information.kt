package com.example.kotlin_project

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Ticket_information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_information)

        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
           finish()
        }
    }

}