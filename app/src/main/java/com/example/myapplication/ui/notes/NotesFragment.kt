package com.example.myapplication.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*

import com.example.myapplication.databinding.FragmentNotesBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesFragment : Fragment() {
    private var persons: MutableList<Goal>? = null

    private var rvNotes: RecyclerView? = null

    private var _binding: FragmentNotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onResume() {

        super.onResume()




        rvNotes = getView()?.findViewById<View>(R.id.rvNotes) as RecyclerView

        val llm = LinearLayoutManager(activity)

        rvNotes?.setLayoutManager(llm)
        rvNotes?.setHasFixedSize(true)

        initializeData()
        initializeAdapter()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





    private fun initializeData() {
        persons = ArrayList<Goal>()

        val db = context?.openOrCreateDatabase("app.db", AppCompatActivity.MODE_PRIVATE, null)


        val context: Context? = activity
        val datab= Database(db)
        var allpoints= datab.getGoals(context)


       // val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale("ru"))

        allpoints=allpoints.reversed()
        for (i in allpoints){
            persons?.add(i)
        }


    }

    private fun initializeAdapter() {
        val adapter = RVnotesAdapter(persons!!)

        rvNotes?.setAdapter(adapter)
    }



}