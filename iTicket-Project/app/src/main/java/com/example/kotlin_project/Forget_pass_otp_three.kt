package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class
import android.widget.ImageButton

class Forget_pass_otp_three : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass_otp_three)
        val confirmPassBtn = findViewById<Button>(R.id.confirm_pass)

        confirmPassBtn.setOnClickListener{
            val confirmPassIntent = Intent(this, Log_in::class.java)
            startActivity(confirmPassIntent)
        }
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            val popBtn = Intent(this, Forget_pass_otp_two::class.java)
            startActivity(popBtn)
        }
    }
}