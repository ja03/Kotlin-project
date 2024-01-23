package com.example.kotlin_project.roomDB.dao

// UserDao.kt
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_project.roomDB.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE user_type = :userType")
    suspend fun getUsersByType(userType: String): List<User>

    @Query("SELECT * FROM users WHERE user_email = :Email")
    suspend fun getUserByEmail(Email: String): User

    @Query("SELECT * FROM users WHERE user_email = :Email")
    suspend fun checkUserByEmail(Email: String): User?
}
