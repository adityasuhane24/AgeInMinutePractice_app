package com.example.ageinminutepractice

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDob.setOnClickListener {view -> clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View){
        val myCalander = Calendar.getInstance()
        val year=myCalander.get(Calendar.YEAR)
        val month=myCalander.get(Calendar.MONTH)
        val dayOfMonth=myCalander.get(Calendar.DAY_OF_MONTH)

      val dpd= DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
          val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear "
            tvSelectedDate.setText(selectedDate)

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            val selectedTime=theDate!!.time / 60000
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentTime=currentDate!!.time / 60000

            val ageInMinutes=currentTime-selectedTime
            tvInMinutes.setText(ageInMinutes.toString())
        }
        ,year, month, dayOfMonth
        )

        dpd.datePicker.setMaxDate(Date().time - 84600000)
        dpd.show()
    }
}