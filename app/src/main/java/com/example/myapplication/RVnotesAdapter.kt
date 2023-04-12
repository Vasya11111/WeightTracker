package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


class RVnotesAdapter  constructor(var goals: MutableList<Goal>) :
    RecyclerView.Adapter<RVnotesAdapter.PersonViewHolder>() {
    class PersonViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var btn: Button
        var cv: CardView
        var measureDate: TextView
        var measureVal: TextView


        init {
            btn= itemView.findViewById<View>(R.id.buttonDelete) as  Button
            cv = itemView.findViewById<View>(R.id.cv) as CardView
            measureDate = itemView.findViewById<View>(R.id.measure_date) as TextView
            measureVal = itemView.findViewById<View>(R.id.measure_val) as TextView

        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PersonViewHolder {
        val v: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.items, viewGroup, false)
        return PersonViewHolder(v)
    }


    override fun onBindViewHolder(personViewHolder: PersonViewHolder, i: Int) {

        personViewHolder.btn.setOnClickListener {

            val viewRelative = it?.parent as ViewGroup
            val child:TextView = viewRelative.getChildAt(0) as TextView
            var text:String = child.text.toString()
            text=text.substring(0,text.length-3)




            val db = it.context.openOrCreateDatabase("app.db", AppCompatActivity.MODE_PRIVATE, null)

            val datab= Database(db)

            datab.deleteNote(text.toInt())

            var viewCard = viewRelative?.parent as ViewGroup
            val viewLinear = viewCard?.parent as ViewGroup
            viewLinear.setPadding(0,0,0,0)
            viewCard.visibility= View.GONE
        }
        personViewHolder.measureDate.text = goals[i].weightDisplay()
        personViewHolder.measureVal.text = goals[i].note

    }

    override fun getItemCount(): Int {
        return goals.size
    }





}
