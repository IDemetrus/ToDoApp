package com.example.todo

import android.app.Application

class ToDoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }
}