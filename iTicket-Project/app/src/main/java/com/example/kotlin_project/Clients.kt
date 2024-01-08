package com.example.kotlin_project

import Data.DataSource
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.databinding.ActivityClientsBinding

class Clients : AppCompatActivity() {
    private lateinit var binding: ActivityClientsBinding

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
        // Populate RecyclerView with data using ClientsAdapter
        val ds = DataSource()
        val name= intent.getStringExtra("nameClient").toString()
        val email=intent.getStringExtra("emailClient").toString()
        val number=intent.getStringExtra("numberClient").toString()
        val clientsList = ds.loadData(name,email,number)
        val clientAdapter = ClientsAdapter(clientsList, this)
        binding.recView.adapter = clientAdapter
    }

}
