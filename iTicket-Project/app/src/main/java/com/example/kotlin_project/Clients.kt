package com.example.kotlin_project

import Data.Client
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.databinding.ActivityClientsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Clients : AppCompatActivity() {
    private lateinit var binding: ActivityClientsBinding
    private lateinit var clientAdapter: ClientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popArrow.setOnClickListener {
            val homePageIntent = Intent(this, Home_page::class.java)
            startActivity(homePageIntent)
        }

        // Set up RecyclerView with LinearLayoutManager
        val layoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = layoutManager

        // Fetch data from Firestore
        getClientInfoFromFirestore()

        // Initialize adapter
        clientAdapter = ClientsAdapter(ArrayList(), this)
        binding.recView.adapter = clientAdapter
    }

    private fun getClientInfoFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("clients")

        collectionRef.get().addOnSuccessListener { documents ->
            val clientsList = mutableListOf<Client>()

            for (document in documents) {
                val name = document.getString("username") ?: ""
                val email = document.getString("email") ?: ""
                val number = document.getString("number") ?: ""

                clientsList.add(Client(name, email, number))
            }

            // Update TextView with the number of clients
            binding.clientsNum.text = clientsList.size.toString()

            // Update RecyclerView with the fetched data
            clientAdapter.updateData(clientsList)
        }
    }
}
