package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_project.dataEmployee.emp
import com.example.kotlin_project.databinding.ActivityEmployeesBinding
import com.google.firebase.firestore.FirebaseFirestore


class Employees : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var empAdapter0: empAdapter
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
        getEmpInfoFromFirestore()
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
}