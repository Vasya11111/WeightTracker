package com.example.myapplication

import java.text.SimpleDateFormat
import java.util.*

open class Measure (_date:Date,_weight:Int){
   public var date:Date=_date
    public var weight:Int=_weight

    public fun dateDisplay():String{

        val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale("ru"))
        var dateString = dateFormatter.format(date)
        return dateString+" г."
    }
    public fun weightDisplay():String{

       return weight.toString()+" КГ"
    }
}