package com.example.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
        @PrimaryKey
        val id: UUID = UUID.randomUUID(),
        var title: String = "",
        var description: String = "",
        var date: Date = Date(),
        var isSolved: Boolean = false,
)