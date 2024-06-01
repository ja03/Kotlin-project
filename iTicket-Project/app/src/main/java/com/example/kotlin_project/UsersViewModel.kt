package com.example.kotlin_project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_project.roomDB.UserRepo
import com.example.kotlin_project.roomDB.entities.User
import kotlinx.coroutines.launch

class UsersViewModel(application: Application):AndroidViewModel (application){

    private val _selectedUserList= MutableLiveData<List<User>>()
    val selectedUserList: LiveData<List<User>> = _selectedUserList

    private val _selectedUserData= MutableLiveData<User>()
    val selectedUserData: LiveData<User> = _selectedUserData

    private val _exist= MutableLiveData<Boolean>()
    val Exist: LiveData<Boolean> = _exist

    private val UserRepo=UserRepo(application)
    fun addUser(name: String, email:String,phone:String,usertype:String,password:String){
        val user= User(
            name=name,
            email=email,
            userType=usertype,
            phoneNumber = phone,
            password = password
        )
        viewModelScope.launch {
            UserRepo.addUser(user)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch{
            UserRepo.deleteAllUsers()
        }
    }
    fun getUserByEmail(email:String){
        viewModelScope.launch{
            var user= UserRepo.getUserByEmail(email)
            _selectedUserData.value=user
        }
    }
   fun checkUserByEmail(email: String,password: String) {
        viewModelScope.launch {
            var user = UserRepo.checkUserByEmail(email,password)
            _exist.value = user != null
        }
    }
    fun getUsersBytype(type: String){
        viewModelScope.launch {
          var Lst=UserRepo.getUsersByType(type)
          _selectedUserList.value=Lst
        }
    }
}