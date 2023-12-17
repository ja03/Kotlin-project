package com.example.kotlin_project

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class filter_tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_tickets)

        val filterBtn = findViewById<Button>(R.id.filter_btn)

        filterBtn.setOnClickListener{
            val filterBtn = Intent(this, Tickets::class.java)
            startActivity(filterBtn)
        }



        var spinnerSortClient:Spinner = findViewById(R.id.spinnerSortClient)
        var adapter01: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.sort_items,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSortClient.adapter = adapter01

        var spinnerSortEmployees:Spinner = findViewById(R.id.spinnerSortEmployees)
        var adapter02: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.sort_items_emp,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSortEmployees.adapter = adapter02

        var spinnerSortState:Spinner = findViewById(R.id.spinnerSortState)
        var adapter03: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.sort_items_state,
            android.R.layout.simple_spinner_dropdown_item
        )
        adapter03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSortState.adapter = adapter03
    }
}