package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlin_project.databinding.ActivityTicketsBinding

class Tickets : AppCompatActivity() {
    private lateinit var binding: ActivityTicketsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketsBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val filterBtn = findViewById<Button>(R.id.filter)
        val ticketElement = findViewById<ConstraintLayout>(R.id.ticket_element)
        filterBtn.setOnClickListener{
            val filterBtn = Intent(this, filter_tickets::class.java)
            startActivity(filterBtn)
        }
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            finish()
        }

    }

}