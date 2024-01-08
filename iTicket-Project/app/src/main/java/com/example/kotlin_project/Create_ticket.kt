package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivityCreateTicketBinding
import com.google.firebase.firestore.FirebaseFirestore

class Create_ticket : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTicketBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        val createTicketBtn = findViewById<Button>(R.id.create_ticket)
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        createTicketBtn.setOnClickListener{
            val createTicketBtn = Intent(this, Tickets::class.java)
            addTicket()
            startActivity(createTicketBtn)
        }
        popBtn.setOnClickListener{
            finish()
        }
    }
    fun addTicket() {
        val db = FirebaseFirestore.getInstance()
        val emp = binding.assEmp.text.toString()
        val title = binding.titleTck.text.toString() // Fix: add .text.toString()
        val des = binding.desc.text.toString()
        val cl:String=intent.getStringExtra("nameCl").toString()
        val ticketInfo = hashMapOf(
            "client" to cl,
            "employee" to emp,
            "state" to "In Progress",
            "title" to title,
            "description" to des,
        )
        db.collection("tickets").document("$cl Ticket $title").set(ticketInfo)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Ticket Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ticket Adding Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

