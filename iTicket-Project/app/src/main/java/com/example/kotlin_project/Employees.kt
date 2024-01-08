package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Employees : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
           finish()
        }
        val addEmpBtn = findViewById<ImageButton>(R.id.add_emp_btn)
        addEmpBtn.setOnClickListener{
            val addBtn = Intent(this, Sign_up::class.java)
            startActivity(addBtn)
        }

    }
}