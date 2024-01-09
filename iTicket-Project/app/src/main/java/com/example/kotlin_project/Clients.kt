package com.example.kotlin_project

import com.example.kotlin_project.dataClient.Client
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
          finish()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = layoutManager
        getClientInfoFromFirestore()
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
            binding.clientsNum.text = clientsList.size.toString()
            clientAdapter.updateData(clientsList)
        }
    }
}
