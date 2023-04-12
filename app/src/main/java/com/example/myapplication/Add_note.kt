package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Database
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import java.util.*


class Add_note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


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
        val noteText : EditText= findViewById<EditText>(R.id.editTextTextNote)


        val datab= Database(db)



        datab.insertGoal(b,noteText.text.toString())



        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", 2);

        startActivity(intent)


    }
}