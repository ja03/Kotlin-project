package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button  // Import the Button class
import android.widget.ImageButton

class Forget_pass_otp_two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass_otp_two)
        val confirmAccountBtn = findViewById<Button>(R.id.confirm_account)
        confirmAccountBtn.setOnClickListener{
            val confirmAccountBtn = Intent(this, Forget_pass_otp_three::class.java)
            startActivity(confirmAccountBtn)
        }
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
            val popBtn = Intent(this, Forget_Pass_OTP_one::class.java)
            startActivity(popBtn)
        }
    }
}