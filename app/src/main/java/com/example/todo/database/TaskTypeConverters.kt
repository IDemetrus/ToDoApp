package com.example.todo.database

import androidx.room.TypeConverter
import java.util.*

class TaskTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?):Long?{
        return date?.time
    }
    @TypeConverter
    fun toDate(millisFrom: Long?) : Date?{
        return millisFrom?.let { Date(it) }
    }
    @TypeConverter
    fun toUUID(uuid: String?): UUID?{
        return UUID.fromString(uuid)
    }
    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }

}