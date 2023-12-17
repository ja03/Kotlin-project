package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class

class Forget_pass_otp_three : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass_otp_three)
        val confirmPassBtn = findViewById<Button>(R.id.confirm_pass)

        confirmPassBtn.setOnClickListener{
            val confirmPassIntent = Intent(this, Log_in::class.java)
            startActivity(confirmPassIntent)
        }
    }
}