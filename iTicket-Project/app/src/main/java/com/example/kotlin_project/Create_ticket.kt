package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Create_ticket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)
        val createTicketBtn = findViewById<Button>(R.id.create_ticket)
        createTicketBtn.setOnClickListener{
            val createTicketBtn = Intent(this, Tickets::class.java)
            startActivity(createTicketBtn)
        }
    }
}