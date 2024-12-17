package com.example.skills53dic.db

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

@Entity(tableName = "data")
data class TicketsSchema(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title", defaultValue = "2022第41屆新一代設計展") val title: String,
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

    @Query("SELECT * FROM data")
    suspend fun getAll(): List<TicketsSchema>
}

@Database(entities = [TicketsSchema::class], version = 1)
abstract class TicketsDataBase : RoomDatabase() {
    abstract fun TicketsDao(): TicketsDao
}

fun getDataBase(context: Context): TicketsDataBase {
    return Room.databaseBuilder(
        context.applicationContext, TicketsDataBase::class.java, "tickets_db"
    ).build()
}

class TicketsViewModel(private val db: TicketsDataBase) : ViewModel() {
    val tickets = mutableStateListOf<TicketsSchema>()

    suspend fun add(tickets: TicketsSchema) {
        db.TicketsDao().insert(tickets)
    }

    init {
        update()
    }

    fun update() {
        viewModelScope.launch {
            tickets.clear()
            tickets.addAll(db.TicketsDao().getAll())
        }
    }

}