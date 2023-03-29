package com.example.myapplication

import android.R.string
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.jjoe64.graphview.series.DataPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Database constructor(_db: SQLiteDatabase?)  {

    val db: SQLiteDatabase? = _db

    fun createDB (context: Context?,name:String){

       //db?.execSQL("DROP TABLE IF EXISTS "+name)
        db?.execSQL("CREATE TABLE IF NOT EXISTS "+name+" (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT UNIQUE, weight INTEGER)")

    }

    fun insertData(currentDate: Calendar, y: Int) {
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
        val dateString = dateFormatter.format(currentDate.time)

        val sqlstring = "INSERT OR REPLACE INTO measures (date,weight) VALUES ('"+dateString+"',"+y+");"

        db?.execSQL(sqlstring)
    }



    fun deleteDate(currentDate: Calendar) {
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
        val dateString = dateFormatter.format(currentDate.time)

        val sqlstring = "DELETE FROM measures WHERE date = '"+dateString+"';"

        db?.execSQL(sqlstring)
    }



    fun insertDots() {

        val date = Calendar.getInstance()
        date.time = Date() // устанавливаем текущую дату
        date.add(Calendar.MONTH, -6)
        insertData(date, 80)


        for(i in 1..5){
            date.add(Calendar.MONTH, 1)
            insertData(date, 70+i*i)
        }


    }



    fun getMeasures(context: Context?): List<Measure> {




        val query = db?.rawQuery("SELECT * FROM measures;", null)

        val Measures = ArrayList<Measure>()

        while (query?.moveToNext()==true) {
            val dateString = query.getString(1)
            val weight = query.getInt(2)




            val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
            val date = dateFormatter.parse(dateString)

            Measures.add(Measure(date, weight))
        }


        var sortedMeasures = Measures.sortedWith(compareBy({ it.date }))


        return sortedMeasures
    }

}