package com.example.kotlin_project

import Data.Client
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.databinding.ActivityClientsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Clients : AppCompatActivity() {
    private lateinit var binding: ActivityClientsBinding
    private lateinit var clientAdapter: ClientsAdapter
    private val viewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popArrow.setOnClickListener {
          finish()
        }
        // Set up RecyclerView with LinearLayoutManager
        val layoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = layoutManager
        // Fetch data from Firestore
       if(isOnline(applicationContext)) {
           getClientInfoFromFirestore()
           getUsersFromFirestore()
       }
       else getClientsInfoFromRoom()
        // Initialize adapter
        clientAdapter = ClientsAdapter(ArrayList(), this)
        binding.recView.adapter = clientAdapter
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
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
    private fun getClientsInfoFromRoom() {

        viewModel.selectedUserList.observe(this, Observer { clients ->
            clients?.let {
                val clientsList = mutableListOf<Client>()
                for(client in clients){
                    var name=client.name
                    var email =client.email
                    var number =client.phoneNumber
                    clientsList.add(Client(name, email, number))
                }
                binding.clientsNum.text = clientsList.size.toString()
                // Update RecyclerView with the fetched data
                clientAdapter.updateData(clientsList)
            }
        })
        viewModel.getUsersBytype("Sign Up as a Client")
    }
    private fun getUsersFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("clients")
        collectionRef.get().addOnSuccessListener { documents ->
            viewModel.deleteAllUsers()
            for (document in documents) {
                val name = document.getString("username") ?: ""
                val email = document.getString("email") ?: ""
                val number = document.getString("number") ?: ""
                val password = document.getString("password") ?: ""
                val usertype = document.getString("typeOfUser") ?: ""
                viewModel.addUser(name,email,number,usertype,password)
            }
            val collectionRef1 = db.collection("employees")
            collectionRef1.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("username") ?: ""
                    val email = document.getString("email") ?: ""
                    val number = document.getString("number") ?: ""
                    val password = document.getString("password") ?: ""
                    val usertype = document.getString("typeOfUser") ?: ""
                    viewModel.addUser(name, email, number, usertype, password)
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch users", Toast.LENGTH_SHORT).show()
        }
    }
}
