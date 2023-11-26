package com.example.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvDiffInMin: TextView? = null

    private fun datePickerFun() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/${selectedYear}"
                tvSelectedDate?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val parsedDate = dateFormat.parse(selectedDate)

                parsedDate?.let {
                    val selectedDateInMin = parsedDate.time / 60000
                    Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()

                    val currentDate =
                        dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currDateInMin = currentDate.time / 60000

                        val diffInMin = currDateInMin - selectedDateInMin
                        tvDiffInMin?.text = "$diffInMin"
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvDiffInMin = findViewById(R.id.tvDiffInMin)

        btnDatePicker.setOnClickListener {
            datePickerFun()
        }


    }
}