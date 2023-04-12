package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



class RVAdapter  constructor(var measures: MutableList<Measure>) :
    RecyclerView.Adapter<RVAdapter.PersonViewHolder>() {
    class PersonViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cv: CardView
        var measureDate: TextView
        var measureVal: TextView


        init {
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
        personViewHolder.measureDate.text = measures[i].dateDisplay()
        personViewHolder.measureVal.text = measures[i].weightDisplay()

    }

    override fun getItemCount(): Int {
        return measures.size
    }
}
