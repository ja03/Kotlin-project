package com.example.kotlin_project

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotlin_project.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Log_in : AppCompatActivity() {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var binding:ActivityLogInBinding
    private var db = Firebase.firestore
    private val viewModel: UsersViewModel by viewModels()

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

                if(isOnline(applicationContext))auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
                            getClientName(email)
                        }
                        else {
                            Toast.makeText(this, "Invalid Email or Wrong Password", Toast.LENGTH_SHORT).show()
                        }
                    }
                else observeUserDataAndCheckExistence(email)

            }
        }
        //----------------------------------
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    private fun observeUserDataAndCheckExistence(email: String) {
        lateinit var fieldName: String
        var exists: Boolean = false

        viewModel.selectedUserData.observe(this, Observer { user ->
            user?.let {
                fieldName = user.name
               handleUserExistence(exists, fieldName, email)
            }
        })

        viewModel.Exist.observe(this, Observer {
            exists = it
            if(exists)viewModel.getUserByEmail(email)
            else Toast.makeText(this, "Invalid Email or Wrong Password", Toast.LENGTH_SHORT).show()
        })

        viewModel.checkUserByEmail(email)
    }

    private fun handleUserExistence(exists: Boolean, fieldName: String, email: String) {
        if (exists) {
            Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
            val signUpIntent = Intent(this, Home_page::class.java)
            signUpIntent.putExtra("fName", fieldName)
            signUpIntent.putExtra("emailCl", email)
            startActivity(signUpIntent)
        } else {
            Toast.makeText(this, "Invalid Email or Wrong Password", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getClientName(email: String){
        //Toast.makeText(this,"${getType(email)},hhhh",Toast.LENGTH_SHORT)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("clients").document(email)
        docRef.get()
            .addOnSuccessListener { docSnap ->
                val fieldName :String= docSnap.getString("username") ?:""
                val signUpIntent = Intent(this, Home_page::class.java)
                signUpIntent.putExtra("fName",fieldName)
                signUpIntent.putExtra("emailCl",email)
                startActivity(signUpIntent)
            }




    }


}