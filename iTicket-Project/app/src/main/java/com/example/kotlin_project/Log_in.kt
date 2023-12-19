package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button  // Import the Button class
import android.widget.Spinner

class Log_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val loginBtn = findViewById<Button>(R.id.log_in_btn)
        val forgetPassBtn = findViewById<Button>(R.id.forget_password_link)
        val signupBtn = findViewById<Button>(R.id.sign_up_link)

        loginBtn.setOnClickListener{
            val loginIntent = Intent(this, Home_page::class.java)
            startActivity(loginIntent)
        }
        forgetPassBtn.setOnClickListener{
            val forgetPassIntent = Intent(this,  Forget_Pass_OTP_one::class.java)
            startActivity(forgetPassIntent)
        }
        signupBtn.setOnClickListener{
            val signupIntent = Intent(this,  Sign_up::class.java)
            startActivity(signupIntent)
        }
        //----------------------------
        var spinnerlogin: Spinner = findViewById(R.id.spinnerlogin)
        var adapter_login: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.LogIn_pos,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerlogin.adapter = adapter_login

    }
}