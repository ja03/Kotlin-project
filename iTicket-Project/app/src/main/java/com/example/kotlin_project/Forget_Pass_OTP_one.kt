package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class

class Forget_Pass_OTP_one : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass_otp_one)

        val sendCodeBtn = findViewById<Button>(R.id.send_code_btn)

        // Fix the typo in the intent variable name
        sendCodeBtn.setOnClickListener{
            val sendCodeIntent = Intent(this, Forget_pass_otp_two::class.java)
            startActivity(sendCodeIntent)
        }
    }
}