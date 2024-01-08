package com.example.kotlin_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_project.databinding.ActivityProfilePageBinding

class ProfilePage : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageBinding
    var email_str: String = ""
    var pass_str: String = ""
    var name_str: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.editInformation.setOnClickListener {
            val editProfileBtn = Intent(this, EditInformation::class.java)
            startActivity(editProfileBtn)
        }

        binding.logout.setOnClickListener {
            val logoutBtn = Intent(this, Log_in::class.java)
            startActivity(logoutBtn)
        }

        binding.popArrow.setOnClickListener {
            val popBtn = Intent(this, Home_page::class.java)
            startActivity(popBtn)
        }

        if (savedInstanceState != null) {
            email_str = savedInstanceState.getString("savedEmail", "")
            pass_str = savedInstanceState.getString("savedPass", "")
            name_str = savedInstanceState.getString("savedName", "")
        }
        else {
            email_str = intent.getStringExtra("emailCl").toString()
            pass_str = intent.getStringExtra("passCl").toString()
            name_str = intent.getStringExtra("nameCl").toString()
        }
        updateTextViews()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var a=outState.putString("savedEmail", email_str)
        var b=outState.putString("savedPass", pass_str)
        var c=outState.putString("savedName", name_str)
        Toast.makeText(this,"store : $email_str , $pass_str , $name_str", Toast.LENGTH_SHORT).show()
    }
    private fun updateTextViews() {
        binding.profEmail.text = email_str
        binding.profPass.text = pass_str
        binding.nameTxt.text = name_str
        Toast.makeText(this,"update : $email_str , $pass_str , $name_str", Toast.LENGTH_SHORT).show()
    }
}
