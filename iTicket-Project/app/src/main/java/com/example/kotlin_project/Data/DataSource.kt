package Data

class DataSource {
    fun loadData(name:String,email:String,number:String):List<Client>{
        return listOf(
            Client(name,email,number)
        )
    }
}