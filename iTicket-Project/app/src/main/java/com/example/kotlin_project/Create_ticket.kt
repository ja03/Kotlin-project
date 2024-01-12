package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.dataTicket.Ticket
import com.example.kotlin_project.databinding.ActivityCreateTicketBinding
import com.example.kotlin_project.databinding.ActivityTicketsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Create_ticket : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTicketBinding
    private lateinit var vbinding: ActivityTicketsBinding
    private lateinit var ticketAdapter: TicketAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTicketBinding.inflate(layoutInflater, null, false)
        vbinding = ActivityTicketsBinding.inflate(layoutInflater, null, false)
        setContentView(vbinding.root)
        setContentView(binding.root)
        val createTicketBtn = findViewById<Button>(R.id.create_ticket)
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        createTicketBtn.setOnClickListener{
            val createTicketBtn = Intent(this, Tickets::class.java)
            addTicket()
            startActivity(createTicketBtn)
            finish()
        }
        popBtn.setOnClickListener{
            finish()
        }
    }
    fun addTicket(){
        val db = FirebaseFirestore.getInstance()
        val emp = binding.assEmp.text.toString()
        val title = binding.titleTck.text.toString()
        val des = binding.desc.text.toString()
        val cl:String=intent.getStringExtra("userNm").toString()
        val ticketInfo = hashMapOf(
            "client" to cl,
            "employee" to emp,
            "state" to "In Progress",
            "title" to title,
            "description" to des,
        )
        db.collection("tickets").document("$cl : Ticket : $title").set(ticketInfo)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val layoutManager = LinearLayoutManager(this)
                    vbinding.recTicket.layoutManager = layoutManager
                    getTicketInfoFromFirestore()
                    ticketAdapter = TicketAdapter(ArrayList(), this)
                    vbinding.recTicket.adapter = ticketAdapter
                    Toast.makeText(this, "Ticket Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ticket Adding Failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun getTicketInfoFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("tickets")
        collectionRef.get().addOnSuccessListener { documents ->
            val ticketList = mutableListOf<Ticket>()
            for (document in documents) {
                val title = document.getString("title") ?: ""
                val state = document.getString("state") ?: ""
                val cl = document.getString("client") ?: ""
                val emp = document.getString("employee") ?: ""
                ticketList.add(Ticket(title,state,cl,emp))
            }
            // Update TextView with the number of clients
            vbinding.tkNum.text = ticketList.size.toString()
            // Update RecyclerView with the fetched data
            ticketAdapter.updateData(ticketList)
        }
    }

}