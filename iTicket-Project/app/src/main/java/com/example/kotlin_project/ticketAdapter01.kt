package com.example.kotlin_project

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.dataTicket.Ticket
import com.example.kotlin_project.databinding.TicketItemBinding

class TicketAdapter(
    private var ticketList: List<Ticket>,
    private val context: Activity
) : RecyclerView.Adapter<TicketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val viewBinding = TicketItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return TicketViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = ticketList.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(ticketList[position])
    }

    fun updateData(newList: List<Ticket>) {
        ticketList = newList
        notifyDataSetChanged()
    }
}

class TicketViewHolder(private val viewBinding: TicketItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(ticket: Ticket) {
        viewBinding.ticketInfo.text = "${ticket.title}\n\n${ticket.client}\n\n${ticket.employee}"
        viewBinding.inProgress01.text = ticket.status
        // Add logic to set other views based on your Ticket data
        // For example, set employee name, client name, etc.
    }
}
