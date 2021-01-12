package com.example.todo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
private const val TAG = "TaskAddFragment"
class TaskAddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_add, container, false)
        val titleTv = view.findViewById(R.id.task_add_title_tv) as TextView
        val descTv = view.findViewById(R.id.task_add_desc_tv) as TextView
        val saveBtn = view.findViewById(R.id.task_add_btn_save) as Button
        val cancelBtn = view.findViewById(R.id.task_add_btn_cancel) as Button

        saveBtn.setOnClickListener {
            if (titleTv.text.isEmpty() || titleTv.text.isBlank())
                Toast.makeText(context, "Write some title", Toast.LENGTH_SHORT).show()
            else{
                Log.d(TAG, titleTv.text.toString())
                Log.d(TAG, descTv.text.toString())
                titleTv.text = ""
                descTv.text = ""
                container?.hideKeyboard()
            }
        }
        cancelBtn.setOnClickListener {
            //TODO back to prev fragment
        }

        return view
    }
    private fun View.hideKeyboard(){
        val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(windowToken,0)
    }
}