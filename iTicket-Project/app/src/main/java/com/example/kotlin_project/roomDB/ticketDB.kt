package com.example.kotlin_project.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin_project.roomDB.dao.TicketDao
import com.example.kotlin_project.roomDB.entities.Ticket

@Database(entities = [Ticket::class], version = 1)
abstract class ticketDB : RoomDatabase() {
    abstract fun TicketDao(): TicketDao
}