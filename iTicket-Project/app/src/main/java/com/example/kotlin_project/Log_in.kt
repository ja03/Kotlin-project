package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Log_in : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding:ActivityLogInBinding
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        // Buttons :

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
                            Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
                            getClientName(email)
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
    private fun getClientName(email: String){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("clients").document(email)
        docRef.get()
            .addOnSuccessListener { docSnap ->
                val fieldName :String= docSnap.getString("username") ?:""
                val loginIntent = Intent(this, Home_page::class.java)
                loginIntent.putExtra("fName",fieldName)
                loginIntent.putExtra("emailCl",email)
                startActivity(loginIntent)
                finish()
            }

    }

}