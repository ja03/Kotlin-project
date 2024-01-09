package com.example.kotlin_project

import com.example.kotlin_project.dataClient.Client
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.databinding.ClientItemBinding

class ClientsAdapter(
    private var clientList: List<Client>,
    private val context: Activity
) : RecyclerView.Adapter<ClientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val viewBinding = ClientItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ClientViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = clientList.size
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(clientList[position])
    }
    fun updateData(newList: List<Client>) {
        clientList = newList
        notifyDataSetChanged()
    }
}

class ClientViewHolder(private val viewBinding: ClientItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(client: Client) {
        viewBinding.clientInfo.text = "${client.name}\n\n${client.email}\n\n${client.number}"
    }
}
