package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.dashboard.Person

class RVAdapter  constructor(var persons: MutableList<Person>) :
    RecyclerView.Adapter<RVAdapter.PersonViewHolder>() {
    class PersonViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cv: CardView
        var personName: TextView
        var personAge: TextView


        init {
            cv = itemView.findViewById<View>(R.id.cv) as CardView
            personName = itemView.findViewById<View>(R.id.person_name) as TextView
            personAge = itemView.findViewById<View>(R.id.person_age) as TextView

        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PersonViewHolder {
        val v: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.tems, viewGroup, false)
        return PersonViewHolder(v)
    }

    override fun onBindViewHolder(personViewHolder: PersonViewHolder, i: Int) {
        personViewHolder.personName.text = persons[i].name
        personViewHolder.personAge.text = persons[i].age

    }

    override fun getItemCount(): Int {
        return persons.size
    }
}
