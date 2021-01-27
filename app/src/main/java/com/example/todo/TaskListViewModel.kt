package com.example.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()

    val taskListLiveData = taskRepository.getTasks()

    fun addTask(task: Task) {

        viewModelScope.launch(Dispatchers.IO) {
            launch {
                taskRepository.addTask(task)
            }
        }

    }

}