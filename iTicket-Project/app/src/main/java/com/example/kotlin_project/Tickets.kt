package com.example.kotlin_project

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.dataTicket.Ticket
import com.example.kotlin_project.databinding.ActivityTicketsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Tickets : AppCompatActivity() {
    private lateinit var binding: ActivityTicketsBinding
    private lateinit var ticketAdapter: TicketAdapter
    private val ticketsList = mutableListOf<Ticket>()
    private var ticketsListTmp = mutableListOf<Ticket>()
    private val viewModel: TicketsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popArrow.setOnClickListener {
            finish()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.recTicket.layoutManager = layoutManager

        if(isOnline(applicationContext))getTicketInfoFromFirestore()
        else{
            getTicketInfoFromRoom()
        }

        ticketAdapter = TicketAdapter(ArrayList(), this)
        binding.recTicket.adapter = ticketAdapter

        binding.filterSw.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ticketsListTmp = ArrayList(ticketsList)
                filterAndSortTitles()
            } else {
                ticketsList.clear()
                ticketsList.addAll(ticketsListTmp)
            }
            ticketAdapter.updateData(ticketsList)
        }
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    private fun getTicketInfoFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("tickets")
        collectionRef.get().addOnSuccessListener { documents ->
            viewModel.deleteAllTickets()
            for (document in documents) {
                val title = document.getString("title") ?: ""
                val status = document.getString("state") ?: ""
                val client = document.getString("client") ?: ""
                val employee = document.getString("employee") ?: ""
                val description = document.getString("description") ?: ""
                ticketsList.add(Ticket(title, status, client, employee))
                viewModel.addTicket(client,description,employee,title,status)
            }

            binding.tkNum.text = ticketsList.size.toString()
            ticketsListTmp = ArrayList(ticketsList)
            ticketAdapter.updateData(ticketsList)
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch tickets", Toast.LENGTH_SHORT).show()
        }
    }
    ///
    private fun getTicketInfoFromRoom() {
        viewModel.selectedTicketsData.observe(this, Observer { ticket ->
            ticket?.let {
                for(tickets in ticket){
                    var client=tickets.client
                    var title =tickets.title
                    var status =tickets.state
                    var employee=tickets.employee
                    ticketsList.add(Ticket(title, status, client, employee))
                }
                binding.tkNum.text = ticketsList.size.toString()
                ticketsListTmp = ArrayList(ticketsList)
                ticketAdapter.updateData(ticketsList)
            }
        })
        viewModel.getAllTickets()
    }

    private fun filterAndSortTitles() {
        ticketsList.clear()
        ticketsList.addAll(ticketsListTmp.sortedBy { it.title})
    }
}