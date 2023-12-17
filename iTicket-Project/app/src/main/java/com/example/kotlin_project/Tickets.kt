package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Filter
import androidx.constraintlayout.widget.ConstraintLayout


class Tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        val filterBtn = findViewById<Button>(R.id.filter)
        val ticketElement = findViewById<ConstraintLayout>(R.id.ticket_element)
        filterBtn.setOnClickListener{
            val filterBtn = Intent(this, Filter::class.java)
            startActivity(filterBtn)
        }
        ticketElement.setOnClickListener{
            val ticketElement = Intent(this, Ticket_information::class.java)
            startActivity(ticketElement)
        }
    }
}