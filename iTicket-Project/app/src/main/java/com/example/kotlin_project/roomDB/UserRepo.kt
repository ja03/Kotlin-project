package com.example.kotlin_project.roomDB

import android.content.Context
import androidx.room.Room
import com.example.kotlin_project.roomDB.entities.User

class UserRepo (appContext: Context){
    private val db= Room.databaseBuilder(
        appContext,
        userDB::class.java,
        "user_db"
    ).build()

    suspend fun addUser(user:User){
        db.userDao().insert(user)
    }
    suspend fun deleteAllUsers(){
        db.userDao().deleteAllUsers()
    }
    suspend fun getUsersByType(userType: String): List<User>{
        return db.userDao().getUsersByType(userType)
    }
    suspend fun getUserByEmail(Email: String): User{
        return db.userDao().getUserByEmail(Email)
    }
    suspend fun checkUserByEmail(Email: String,Password: String): User?{
        return db.userDao().checkUserByEmail(Email,Password)
    }
}