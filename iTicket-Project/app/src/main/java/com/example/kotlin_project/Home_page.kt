package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivityHomePageBinding
import com.google.firebase.firestore.FirebaseFirestore

class Home_page  : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    var tmp_email:String=""
    var tmp_pass:String=""
    var tmp_name:String=""
    var str:String=""
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        binding.newTaskImg.setOnClickListener{
            val x = intent.getStringExtra("fName").toString()
            var newTaskBtn = Intent(this, Create_ticket::class.java)
            newTaskBtn.putExtra("userNm",x)
            startActivity(newTaskBtn)
        }
        binding.profilePage.setOnClickListener{
            val email = intent.getStringExtra("emailCl").toString()
            getClientInfo(email)
        }
        binding.ticketsPage.setOnClickListener{
            val ticketsBtn = Intent(this, Tickets::class.java)
            startActivity(ticketsBtn)
        }
        binding.clientsPage.setOnClickListener { val clientsBtn = Intent(this, Clients::class.java)
            val email02 = intent.getStringExtra("emailCl").toString()
            getClientInfo02(email02)
        }
        binding.employeesPage.setOnClickListener {
            val employeesBtn = Intent(this, Employees::class.java)
            startActivity(employeesBtn)
        }
        //---------------------------
        if (savedInstanceState == null) {
            str = intent.getStringExtra("fName").toString()
        } else {
            str = savedInstanceState.getString("savedName", "")
        }
        updateTextViews()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedName",str)
    }
    private fun updateTextViews() {
        val hello_ed = binding.helloTxt
        val header_name = binding.dashName
        hello_ed.text = "Hello $str 👋"
        header_name.text = "$str\nDashboard"
    }
    private fun getClientInfo(email: String){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("clients").document(email)
        docRef.get()
            .addOnSuccessListener { docSnap ->
                val fieldEmail = docSnap.getString("email") ?:""
                val fieldPass = docSnap.getString("password") ?:""
                val fieldName = docSnap.getString("username") ?:""
                tmp_email=fieldEmail
                tmp_pass=fieldPass
                tmp_name=fieldName
                val profBtn = Intent(this, ProfilePage::class.java)
                profBtn.putExtra("emailCl",fieldEmail)
                profBtn.putExtra("passCl",fieldPass)
                profBtn.putExtra("nameCl",fieldName)
                startActivity(profBtn)
            }
    }
    private fun getClientInfo02(email: String){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("clients").document(email)
        docRef.get()
            .addOnSuccessListener { docSnap ->
                val fieldEmail = docSnap.getString("email") ?:""
                val fieldPass = docSnap.getString("password") ?:""
                val fieldName = docSnap.getString("number") ?:""
                val clBtn = Intent(this, Clients::class.java)
                clBtn.putExtra("emailClient",fieldEmail)
                clBtn.putExtra("passClient",fieldPass)
                clBtn.putExtra("numClient",fieldName)
                startActivity(clBtn)
            }
    }
}