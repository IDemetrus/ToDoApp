package com.example.todo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TaskFragment"

class TaskFragment : Fragment() {

    private lateinit var task: Task
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    private lateinit var viewModel: TaskListViewModel
    private lateinit var taskListFragment: TaskListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = Task()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
        taskListFragment = TaskListFragment()

        titleField = view.findViewById(R.id.task_title) as EditText
        dateButton = view.findViewById(R.id.task_date) as Button
        dateButton.apply {
            text = getDate(task.date)
            isEnabled = true
        }
        solvedCheckBox = view.findViewById(R.id.task_solved) as CheckBox



        return view
    }

    private fun getDate(date: Date): String {
        val sdf = SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH)
        return sdf.format(date)
    }

    override fun onStart() {
        super.onStart()
        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked -> task.isSolved = isChecked }
        }
        dateButton.setOnClickListener {
            task.title = titleField.text.toString()
            viewModel.addTask(task)
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, taskListFragment)
                ?.commit()
        }
    }

}