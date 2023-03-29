package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Add_measure : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_measure)


        val numberPicker: NumberPicker = findViewById(R.id.numberPicker)
        numberPicker.setMaxValue(200);
        numberPicker.setMinValue(20);
        numberPicker.setValue(75);
        numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS // блокируем появление клавиатуры


    }


    fun onClickAdd(view: View?) {

        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)

        val picker : NumberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        val b =picker.value



        val datab= Database(db)


        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year



        val date = Calendar.getInstance()
        date.time = Date(year-1900 , month, day)
        datab.insertData(date,b)



        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", 1);

        startActivity(intent)


    }
}