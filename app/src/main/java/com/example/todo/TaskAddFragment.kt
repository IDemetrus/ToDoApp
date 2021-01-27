package com.example.todo

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TaskAddFragment"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val ARG_DATE = "date"

class TaskAddFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var task: Task
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var datePickerFragment: DatePickerFragment
    private lateinit var viewModel: TaskListViewModel


    private lateinit var titleEt: EditText
    private lateinit var descEt: EditText
    private lateinit var dateBtn: Button
    private lateinit var date: Date
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = Task()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_add, container, false)

        taskListFragment = TaskListFragment()
        datePickerFragment = DatePickerFragment()
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        saveBtn = view.findViewById(R.id.task_add_save_btn)
        titleEt = view.findViewById(R.id.task_add_title_et)
        descEt = view.findViewById(R.id.task_add_desc_et)
        dateBtn = view.findViewById(R.id.task_add_date_btn)


        return view
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "--> onStart")
        Log.d(TAG, "${formatDate(date)}")

        dateBtn.setOnClickListener {
            datePickerFragment.show(requireFragmentManager(),"datePicker")
        }

        saveBtn.setOnClickListener {

            task.title = titleEt.text.toString()
            task.description = descEt.text.toString()
            task.date = date
            viewModel.addTask(task)

            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, taskListFragment)
                ?.commit()
        }
    }

    private fun formatDate(date: Date): String? {
        val sdf = SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH)
        return sdf.format(date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = GregorianCalendar(year, month, dayOfMonth).time
        dateBtn.text = formatDate(date)
    }

}