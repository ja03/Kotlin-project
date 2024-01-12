package com.example.kotlin_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.dataTicket.Ticket
import com.example.kotlin_project.databinding.ActivityTicketsBinding
import com.google.firebase.firestore.FirebaseFirestore
class Tickets : AppCompatActivity() {
    private lateinit var binding: ActivityTicketsBinding
    private lateinit var ticketAdapter: TicketAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popArrow.setOnClickListener {
            finish()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recTicket.layoutManager = layoutManager
        getTicketInfoFromFirestore()
        ticketAdapter = TicketAdapter(ArrayList(), this)
        binding.recTicket.adapter = ticketAdapter
    }

    private fun getTicketInfoFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("tickets")

        collectionRef.get().addOnSuccessListener { documents ->
            val ticketsList = mutableListOf<Ticket>()

            for (document in documents) {
                val title = document.getString("title") ?: ""
                val status = document.getString("state") ?: ""
                val client = document.getString("client") ?: ""
                val employee = document.getString("employee") ?: ""
                ticketsList.add(Ticket(title, status, client, employee))
            }
            binding.tkNum.text = ticketsList.size.toString()
            ticketAdapter.updateData(ticketsList)
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch tickets", Toast.LENGTH_SHORT).show()
        }
    }
}
