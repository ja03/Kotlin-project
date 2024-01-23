package com.example.kotlin_project

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotlin_project.databinding.ActivityHomePageBinding
import com.google.firebase.firestore.FirebaseFirestore

class Home_page  : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    var tmp_email:String=""
    var tmp_pass:String=""
    var tmp_name:String=""
    var str:String=""
    private val viewModel: UsersViewModel by viewModels()
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
            val clBtn = Intent(this, Clients::class.java)
            startActivity(clBtn)
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
    private fun updateTextViews(){
        val hello_ed = binding.helloTxt
        val header_name = binding.dashName
        hello_ed.text = "Hello $str 👋"
        header_name.text = "$str\nDashboard"
    }
    private fun getClientInfo(email: String){
        if(isOnline(applicationContext)) {
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("clients").document(email)
            docRef.get()
                .addOnSuccessListener { docSnap ->
                    val fieldEmail = docSnap.getString("email") ?: "NONE"
                    if (fieldEmail != "NONE") {
                        val fieldPass = docSnap.getString("password") ?: ""
                        val fieldName = docSnap.getString("username") ?: ""
                        tmp_email = fieldEmail
                        tmp_pass = fieldPass
                        tmp_name = fieldName
                        val profBtn = Intent(this, ProfilePage::class.java)
                        profBtn.putExtra("emailCl", fieldEmail)
                        profBtn.putExtra("passCl", fieldPass)
                        profBtn.putExtra("nameCl", fieldName)
                        startActivity(profBtn)
                    } else {
                        val docRef = db.collection("employees").document(email)
                        docRef.get()
                            .addOnSuccessListener { docSnap ->
                                val fieldEmail = docSnap.getString("email") ?: "NONE"
                                if (fieldEmail != "NONE") {
                                    val fieldPass = docSnap.getString("password") ?: ""
                                    val fieldName = docSnap.getString("username") ?: ""
                                    tmp_email = fieldEmail
                                    tmp_pass = fieldPass
                                    tmp_name = fieldName
                                    val profBtn = Intent(this, ProfilePage::class.java)
                                    profBtn.putExtra("emailCl", fieldEmail)
                                    profBtn.putExtra("passCl", fieldPass)
                                    profBtn.putExtra("nameCl", fieldName)
                                    startActivity(profBtn)
                                }
                            }

                    }
                }
        }
        else{
            viewModel.selectedUserData.observe(this , Observer{ user ->
                user?.let {
                    val fieldEmail=user.email
                    val fieldPass=user.password
                    val fieldName=user.name
                    tmp_email=fieldEmail
                    tmp_pass=fieldPass
                    tmp_name=fieldName
                    val profBtn = Intent(this, ProfilePage::class.java)
                    profBtn.putExtra("emailCl",fieldEmail)
                    profBtn.putExtra("passCl",fieldPass)
                    profBtn.putExtra("nameCl",fieldName)
                    startActivity(profBtn)
                }
            })
            viewModel.getUserByEmail(email)

        }
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