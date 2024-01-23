package com.example.kotlin_project.roomDB

import android.content.Context
import androidx.room.Room
import com.example.kotlin_project.roomDB.entities.Ticket

class TicketRepo(appContext: Context) {
    private val db= Room.databaseBuilder(
        appContext,
        ticketDB::class.java,
        "ticket_db"
    ).build()

    suspend fun addTicket(ticket: Ticket){
        db.TicketDao().insert(ticket)
    }

    suspend fun deleteAllTickets(){
        db.TicketDao().deleteAllTickets()
    }
    suspend fun getAllTickets(): List<Ticket>{
        return db.TicketDao().getAllTickets()
    }


    suspend fun getTicketById(ticketId: Long): List<Ticket> {
        return db.TicketDao().getTicketById(ticketId)
    }
}