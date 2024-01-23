package com.example.kotlin_project.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int = 0,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_email")
    val email: String,

    @ColumnInfo(name = "user_phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "user_type")
    val userType: String,

    @ColumnInfo(name="user_password")
    val password:String
)