package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signupBtn = findViewById<Button>(R.id.sign_up_btn)
        val loginBtn = findViewById<Button>(R.id.log_in_btn)
        signupBtn.setOnClickListener{
            val signupIntent = Intent(this, Home_page::class.java)
            startActivity(signupIntent)
        }
        loginBtn.setOnClickListener{
            val loginIntent = Intent(this, Log_in::class.java)
            startActivity(loginIntent)
        }
    }
}