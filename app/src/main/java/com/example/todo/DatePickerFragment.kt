package com.example.todo

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment() {

//    private var date = Date()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireActivity(),
            null,
            year,
            month,
            day
        )
    }

}