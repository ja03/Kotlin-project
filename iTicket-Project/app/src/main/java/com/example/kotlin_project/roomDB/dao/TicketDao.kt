package com.example.kotlin_project.roomDB.dao
// TicketDao.kt
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_project.roomDB.entities.Ticket

@Dao
interface TicketDao {
    @Insert
    suspend fun insert(ticket: Ticket)

    @Query("DELETE FROM tickets")
    suspend fun deleteAllTickets()

    @Query("SELECT * FROM tickets")
    suspend fun getAllTickets(): List<Ticket>

    @Query("SELECT * FROM tickets WHERE ticket_id = :ticketId")
    suspend fun getTicketById(ticketId: Long): List<Ticket>

}
