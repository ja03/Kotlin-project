package com.example.kotlin_project

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Welcoming_one : AppCompatActivity() {
    private val viewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcoming_one)

        // Fix the typo in the button variable name
        val learnMoreBtn = findViewById<Button>(R.id.learn_more_btn)

        // Fix the typo in the intent variable name
        learnMoreBtn.setOnClickListener{
            if(isOnline(applicationContext)){
                getUsersFromFirestore()
            }
            val learnMoreIntent = Intent(this, Log_in::class.java)
            startActivity(learnMoreIntent)

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
