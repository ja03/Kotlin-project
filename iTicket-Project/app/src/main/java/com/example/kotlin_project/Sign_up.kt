package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Sign_up : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivitySignUpBinding
    private var db = Firebase.firestore
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
        binding.signUpBtn.setOnClickListener {
            val email = binding.signupEmailTxt.text.toString().trim()
            val username = binding.userNameTxt.text.toString()
            val password = binding.signPasswordTxt.text.toString().trim()
            val confPass = binding.confPasswordTxt.text.toString().trim()
            val number = binding.numberEt.text.toString()
            if (email.isBlank()) Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            else if(username.isBlank())Toast.makeText(this, "Enter User Name ", Toast.LENGTH_SHORT).show()
            else if (password.isBlank())Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            else if(confPass.isBlank())Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
            else if(number.isBlank())Toast.makeText(this, "Enter Number", Toast.LENGTH_SHORT).show()
            else if(password.length<8)Toast.makeText(this, "Password must be at least 8 letters", Toast.LENGTH_SHORT).show()
            else if(password.length>38)Toast.makeText(this, "Password must be at most 38 letters", Toast.LENGTH_SHORT).show()
            else if(!checkCap(password))Toast.makeText(this, "Password must have at least one Capital letter", Toast.LENGTH_SHORT).show()
            else if(!checkSmall(password))Toast.makeText(this, "Password must have at least one Small letter", Toast.LENGTH_SHORT).show()
            else if(password!=confPass)Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            else {
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            addClient(email,password,username,number)
                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                            val done=Intent(this, Log_in::class.java)
                            startActivity(done)
                        }
                        else{
                            Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        //-------------------------------
    }
    fun addClient(email:String,password:String,username:String,number:String){
        val clientInfo = hashMapOf(
            "email" to email,
            "password" to password,
            "username" to username,
            "number" to number
        )
        db.collection("clients").document(binding.signupEmailTxt.text.toString()).set(clientInfo)
            .addOnCompleteListener{
                if(it.isSuccessful)Toast.makeText(this,"Account Added",Toast.LENGTH_SHORT)
                else  Toast.makeText(this,"Account Adding Failed",Toast.LENGTH_SHORT)
            }
    }
    private fun checkCap(str:String):Boolean{
        for(c in str){
            if(c.isUpperCase())return true
        }
        return false
    }
    fun checkSmall(str:String):Boolean{
        for(c in str){
            if(c.isLowerCase())return true
        }
        return false
    }
}