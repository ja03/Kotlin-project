package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class
import android.widget.ImageButton

class Home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val newTaskBtn = findViewById<ImageButton>(R.id.new_task_img)
        val profileBtn = findViewById<ImageButton>(R.id.profile_page)
        val ticketsBtn = findViewById<Button>(R.id.tickets_page)
        val clientsBtn = findViewById<Button>(R.id.clients_page)
        val employeesBtn = findViewById<Button>(R.id.employees_page)

        newTaskBtn.setOnClickListener{
            val newTaskBtn = Intent(this, Create_ticket::class.java)
            startActivity(newTaskBtn)
        }
        profileBtn.setOnClickListener{
            val profileBtn = Intent(this, ProfilePage::class.java)
            startActivity(profileBtn)
        }
        ticketsBtn.setOnClickListener{
            val ticketsBtn = Intent(this, Tickets::class.java)
            startActivity(ticketsBtn)
        }
        clientsBtn.setOnClickListener {
            val clientsBtn = Intent(this, Clients::class.java)
            startActivity(clientsBtn)
        }
        employeesBtn.setOnClickListener {
            val employeesBtn = Intent(this, Clients::class.java)
            startActivity(employeesBtn)
        }


        }
}