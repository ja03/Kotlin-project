package com.example.kotlin_project.dataTicket

class DataSourceTicket {
    fun loadData(title:String,status:String,client:String,employee:String):List<Ticket>{
        return listOf(
             Ticket("fkkkk","dann","in pro","mo5")
        )
    }
}