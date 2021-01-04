package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TaskListFragment"

class TaskListFragment : Fragment() {

    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProvider(this).get(TaskListViewModel::class.java)
    }
    private lateinit var taskRecyclerView: RecyclerView

    companion object {
        fun newInstance() : TaskListFragment{
            return TaskListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total tasks: ${taskListViewModel.tasks.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list,container,false)

        taskRecyclerView = view.findViewById(R.id.task_recycler_view) as RecyclerView
        taskRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

}