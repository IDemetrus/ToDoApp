package com.example.todo

import java.util.*

data class Task(
        val id: UUID = UUID.randomUUID(),
        var title: String = "",
        var date: Date = Date(),
        var isSolved: Boolean = false,
        var requiredManage: Int = 0
)