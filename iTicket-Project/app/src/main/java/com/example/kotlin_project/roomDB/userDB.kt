package com.example.kotlin_project.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin_project.roomDB.dao.UserDao
import com.example.kotlin_project.roomDB.entities.User

@Database(entities = [User::class], version = 1)
abstract class userDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}
