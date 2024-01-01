package com.example.kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.kotlin_project.databinding.ActivityLogInBinding
import com.example.kotlin_project.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class Sign_up : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.logInBtn.setOnClickListener{
            val loginIntent = Intent(this, Log_in::class.java)
            startActivity(loginIntent)
        }
        //-------------
        var spinnersignup: Spinner = findViewById(R.id.spinnerSignUp)
        var adapter_signup: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.SignUp_pos,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnersignup.adapter = adapter_signup
        // Sign Up : ------------------------------------
        binding.signUpBtn.setOnClickListener{
            var email = binding.emailTxt.text.toString()
            var password = binding.passwordTxt.text.toString()
            if(email.isBlank()){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            }
            else if(password.isBlank()){
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                            val signupIntent = Intent(this, Home_page::class.java)
                            startActivity(signupIntent)
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }



        //-------------------------------
    }
}