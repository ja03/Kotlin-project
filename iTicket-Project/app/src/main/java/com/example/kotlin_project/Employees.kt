package com.example.kotlin_project

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.dataEmployee.emp
import com.example.kotlin_project.databinding.ActivityEmployeesBinding
import com.google.firebase.firestore.FirebaseFirestore


class Employees : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var empAdapter0: empAdapter
    private val viewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val popBtn = findViewById<ImageButton>(R.id.pop_arrow)
        popBtn.setOnClickListener{
           finish()
        }
        val addEmpBtn = findViewById<ImageButton>(R.id.add_emp_btn)
        addEmpBtn.setOnClickListener{
            val addBtn = Intent(this, Sign_up::class.java)
            startActivity(addBtn)
        }
        //------------------
        val layoutManager = LinearLayoutManager(this)
        binding.recEmp.layoutManager = layoutManager

        if(isOnline(applicationContext)){
            getEmpInfoFromFirestore()
            getUsersFromFirestore()
        }
        else getEmpInfoFromRoom()
        empAdapter0= empAdapter(ArrayList(), this)
        binding.recEmp.adapter = empAdapter0
    }
    private fun getEmpInfoFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("employees")
        collectionRef.get().addOnSuccessListener { documents ->
            val empList = mutableListOf<emp>()
            for (document in documents) {
                val name = document.getString("username") ?: ""
                val email = document.getString("email") ?: ""
                val number = document.getString("number") ?: ""

                empList.add(emp(name, email, number))
            }
            binding.empSz.text = empList.size.toString()
            empAdapter0.updateData(empList)
        }
    }
    private fun getUsersFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("clients")
        collectionRef.get().addOnSuccessListener { documents ->
            viewModel.deleteAllUsers()
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
    private fun getEmpInfoFromRoom() {

        viewModel.selectedUserList.observe(this, Observer { clients ->
            clients?.let {
                val empList = mutableListOf<emp>()
                for(client in clients){
                    var name=client.name
                    var email =client.email
                    var number =client.phoneNumber
                    empList.add(emp(name, email, number))
                }
                binding.empSz.text = empList.size.toString()
                empAdapter0.updateData(empList)
            }
        })
        viewModel.getUsersBytype("Sign Up as an Employee")
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}