package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Create_ticket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)
        val createTicketBtn = findViewById<Button>(R.id.create_ticket)
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)

        createTicketBtn.setOnClickListener{
            val createTicketBtn = Intent(this, Tickets::class.java)
            startActivity(createTicketBtn)
        }

        popBtn.setOnClickListener{
            finish()
        }
    }
}