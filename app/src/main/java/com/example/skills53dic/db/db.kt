package com.example.skills53dic.db

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch

@Entity(tableName = "tickets")
data class TicketsSchema(
    @PrimaryKey val id: String,
    val title: String = "2022第41屆新一代設計展",
    val type: String,
    val name: String,
    val email: String,
    val phone: String,
    val date: String,
    val price: String
)

@Dao
interface TicketsDao {
    @Insert
    suspend fun insert(tickets: TicketsSchema)

    @Query("SELECT * FROM tickets")
    suspend fun getAll(): List<TicketsSchema>
}

@Database(entities = [TicketsSchema::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun TicketsDao(): TicketsDao
}

fun getDataBase(context: Context): DataBase {
//    context.deleteDatabase("db")
    return Room.databaseBuilder(
        context.applicationContext, DataBase::class.java, "db"
    ).build()
}

class TicketsViewModel(private val db: DataBase) : ViewModel() {
    val tickets = mutableStateListOf<TicketsSchema>()

    init {
        update()
    }

    fun add(data: TicketsSchema) {
        viewModelScope.launch {
            db.TicketsDao().insert(data)
            update()
        }
    }

    fun update() {
        viewModelScope.launch {
            tickets.clear()
            tickets.addAll(db.TicketsDao().getAll())
        }
    }
}