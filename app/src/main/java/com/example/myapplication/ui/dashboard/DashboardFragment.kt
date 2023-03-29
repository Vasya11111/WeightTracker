package com.example.myapplication.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.databinding.FragmentDashboardBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList




class Person(var name: String, var age: String)




class DashboardFragment : Fragment() {

    private var persons: MutableList<Person>? = null
    private var rv: RecyclerView? = null


    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }


    override fun onResume() {

        super.onResume()

        rv = getView()?.findViewById<View>(R.id.rv) as RecyclerView

        val llm = LinearLayoutManager(activity)

        rv?.setLayoutManager(llm)
        rv?.setHasFixedSize(true)

        initializeData()
        initializeAdapter()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    private fun initializeData() {
        persons = ArrayList<Person>()

        val db = context?.openOrCreateDatabase("app.db", AppCompatActivity.MODE_PRIVATE, null)


        val context: Context? = activity
        val datab= Database(db)
        var allpoints= datab.getMeasures(context)


        val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale("ru"))

        allpoints=allpoints.reversed()
        for (i in allpoints){
            var dateString = dateFormatter.format(i.date)
            persons?.add(Person(dateString+" г.", i.weight.toString()+" КГ"))
        }


    }

    private fun initializeAdapter() {
        val adapter = RVAdapter(persons!!)

        rv?.setAdapter(adapter)
    }


}