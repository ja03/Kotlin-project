package com.example.kotlin_project

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.dataTicket.Ticket
import com.example.kotlin_project.databinding.TicketItemBinding

class ticketAdapter(
    private var ticketList: List<Ticket>,
    private val context: Activity
) : RecyclerView.Adapter<ticketAdapter.TicketViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder{
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
    class TicketViewHolder(private val viewBinding: TicketItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(tk : Ticket) {
            viewBinding.ticketInfo.text = "${tk.title}\n\n${tk.client}\n\n${tk.employee}"
            viewBinding.inProgress01.text =tk.status
            // You can customize further bindings or actions based on your requirements
            // For example, handling clicks on different icons, etc.
        }
    }
}


