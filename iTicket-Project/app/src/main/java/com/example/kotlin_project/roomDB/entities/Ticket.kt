package com.example.kotlin_project.roomDB.entities

// Ticket.kt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ticket_id")
    val id: Long = 0,

    @ColumnInfo(name = "client")
    val client: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "employee")
    val employee: String,

    @ColumnInfo(name = "state")
    val state: String,

    @ColumnInfo(name = "title")
    val title: String
)
