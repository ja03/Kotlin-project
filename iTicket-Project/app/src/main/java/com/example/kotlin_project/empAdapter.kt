package com.example.kotlin_project

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.dataEmployee.emp
import com.example.kotlin_project.databinding.EmpItemBinding

class empAdapter(
    private var empList: List<emp>,
    private val context: Activity
) : RecyclerView.Adapter<empViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): empViewHolder {
        val viewBinding = EmpItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return empViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = empList.size
    override fun onBindViewHolder(holder: empViewHolder, position: Int) {
        holder.bind(empList[position])
    }
    fun updateData(newList: List<emp>) {
        empList = newList
        notifyDataSetChanged()
    }
}

class empViewHolder(private val viewBinding: EmpItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(e: emp) {
        viewBinding.empInfo.text = "${e.name}\n\n${e.email}\n\n${e.number}"
    }
}
