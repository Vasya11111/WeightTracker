package com.example.myapplication

import java.util.*

class Goal (_weight:Int,_note:String){
    public var weight: Int =_weight
    public var note:String=_note

    public fun weightDisplay():String{

        return weight.toString()+" КГ"
    }

}