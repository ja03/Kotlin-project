package com.example.kotlin_project

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button  // Import the Button class
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.content.ContentValues.TAG
import android.view.View
import android.widget.EditText
import android.widget.SimpleAdapter.ViewBinder
import com.example.kotlin_project.databinding.ActivityLogInBinding

class Log_in : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding:ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        // Buttons :
        val signupBtn = findViewById<Button>(R.id.sign_up_link)
        binding.forgetPasswordLink.setOnClickListener{
            val forgetPassIntent = Intent(this,  Forget_Pass_OTP_one::class.java)
            startActivity(forgetPassIntent)
        }
        binding.signUpLink.setOnClickListener{
            val signupIntent = Intent(this,  Sign_up::class.java)
            startActivity(signupIntent)
        }
        //----------------------------
        // Spinners :
        var spinnerlogin: Spinner = findViewById(R.id.spinnerlogin)
        var adapter_login: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.LogIn_pos,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerlogin.adapter = adapter_login
        //--------------------------------
        // Log in :
        binding.logInBtn.setOnClickListener{
            var email = binding.emailTxt.text.toString()
            var password = binding.passwordTxt.text.toString()
            if(email.isBlank()){
                Toast.makeText(this, "Enter Email",Toast.LENGTH_SHORT).show()
            }
            else if(password.isBlank()){
                Toast.makeText(this, "Enter Password",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "Log in successful",Toast.LENGTH_SHORT).show()
                            val loginIntent = Intent(this, Home_page::class.java)
                            startActivity(loginIntent)
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Invalid Email or Wrong Password",Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }




        //----------------------------------
    }

}