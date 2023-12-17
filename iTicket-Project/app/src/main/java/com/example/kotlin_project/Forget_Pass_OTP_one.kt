package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class
import android.widget.ImageButton

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
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            val popBtn = Intent(this, Log_in::class.java)
            startActivity(popBtn)
        }
    }
}