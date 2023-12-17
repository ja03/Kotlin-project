package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class

class Forget_pass_otp_two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass_otp_two)
        val confirmAccountBtn = findViewById<Button>(R.id.confirm_account)
        confirmAccountBtn.setOnClickListener{
            val confirmAccountBtn = Intent(this, Forget_pass_otp_three::class.java)
            startActivity(confirmAccountBtn)
        }
    }
}