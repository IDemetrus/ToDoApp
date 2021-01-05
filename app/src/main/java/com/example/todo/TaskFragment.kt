package com.example.todo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class TaskFragment : Fragment() {

    private lateinit var task: Task
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

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

        titleField = view.findViewById(R.id.task_title) as EditText
        dateButton = view.findViewById(R.id.task_date) as Button
        dateButton.apply {
            text = getDate(task.date)
            isEnabled = false
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

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked -> task.isSolved = isChecked }
        }
    }
}