package com.example.kotlin_project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_project.roomDB.TicketRepo
import com.example.kotlin_project.roomDB.entities.Ticket
import kotlinx.coroutines.launch

class TicketsViewModel(application: Application): AndroidViewModel(application) {
    private val _selectedTicketsData= MutableLiveData<List<Ticket>>()
    val selectedTicketsData: LiveData<List<Ticket>> = _selectedTicketsData

    private val _exist= MutableLiveData<Boolean>()
    val Exist: LiveData<Boolean> = _exist

    private val TicketRepo= TicketRepo(application)
    fun addTicket(client : String, description :String,employee:String,title:String,state:String){
        val ticket= Ticket(
            client = client,
            description = description,
            employee = employee,
            title = title,
            state = state
        )
        viewModelScope.launch {
            TicketRepo.addTicket(ticket)
        }
    }
    fun deleteAllTickets(){
        viewModelScope.launch {
            TicketRepo.deleteAllTickets()
        }
    }
    fun getAllTickets(){
        viewModelScope.launch {
            var a=TicketRepo.getAllTickets()
            _selectedTicketsData.value=a
        }
    }
}