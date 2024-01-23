package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Sign_up : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivitySignUpBinding
    private var db = Firebase.firestore
    private val viewModel: UsersViewModel by viewModels()
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
            val Spinner:Spinner=findViewById(R.id.spinnerSignUp)
            val typeOfUser = Spinner.getSelectedItem().toString();

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
                            addOb(email,password,username,number,typeOfUser)
                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                            getClientName(email)
                        }
                        else{
                            Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        //-------------------------------
    }
    fun addOb(email:String,password:String,username:String,number:String,typeOfUser:String){
        val info = hashMapOf(
            "typeOfUser" to typeOfUser,
            "email" to email,
            "password" to password,
            "username" to username,
            "number" to number
        )
        if(typeOfUser=="Sign Up as an Employee"){
            db.collection("employees").document(email).set(info)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this,"Account Added",Toast.LENGTH_SHORT)
                        getUsersFromFirestore()
                    }
                    else  Toast.makeText(this,"Account Adding Failed",Toast.LENGTH_SHORT)
                }
        }
        else{
            db.collection("clients").document(email).set(info)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this,"Account Added",Toast.LENGTH_SHORT)
                        getUsersFromFirestore()
                    }
                    else  Toast.makeText(this,"Account Adding Failed",Toast.LENGTH_SHORT)
                }
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
    private fun getClientName(email: String){
        //Toast.makeText(this,"${getType(email)},hhhh",Toast.LENGTH_SHORT)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("clients").document(email)
        docRef.get()
            .addOnSuccessListener { docSnap ->
                val fieldName :String= docSnap.getString("username") ?:"NONE"
                if(fieldName!="NONE"){
                    val signUpIntent = Intent(this, Home_page::class.java)
                    signUpIntent.putExtra("fName",fieldName)
                    signUpIntent.putExtra("emailCl",email)
                    startActivity(signUpIntent)
                }
                else{
                    val docRef = db.collection("employees").document(email)
                    docRef.get()
                        .addOnSuccessListener { docSnap ->
                            val fieldName :String= docSnap.getString("username") ?:"NONE"
                            if(fieldName!="NONE"){
                                val signUpIntent = Intent(this, Home_page::class.java)
                                signUpIntent.putExtra("fName",fieldName)
                                signUpIntent.putExtra("emailCl",email)
                                startActivity(signUpIntent)
                            }

                        }
                }
            }
    }

    private fun getUsersFromFirestore() {
        viewModel.deleteAllUsers()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("clients")
        collectionRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val name = document.getString("username") ?: ""
                val email = document.getString("email") ?: ""
                val number = document.getString("number") ?: ""
                val password = document.getString("password") ?: ""
                val usertype = document.getString("typeOfUser") ?: ""
                viewModel.addUser(name,email,number,usertype,password)
            }
            val collectionRef1 = db.collection("employees")
            collectionRef1.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("username") ?: ""
                    val email = document.getString("email") ?: ""
                    val number = document.getString("number") ?: ""
                    val password = document.getString("password") ?: ""
                    val usertype = document.getString("typeOfUser") ?: ""
                    viewModel.addUser(name, email, number, usertype, password)
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch users", Toast.LENGTH_SHORT).show()
        }
    }

}