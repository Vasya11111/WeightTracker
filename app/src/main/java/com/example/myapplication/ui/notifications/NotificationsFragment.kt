package com.example.myapplication.ui.notifications





import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Database
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*




class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null





    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
                inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onResume() {

       val db = context?.openOrCreateDatabase("app.db", AppCompatActivity.MODE_PRIVATE, null)



        val context: Context? = activity
       val datab= Database(db)
       val allpoints= datab.getMeasures(context)




        val dataPoints = ArrayList<DataPoint>()

        val series = LineGraphSeries<DataPoint>()

for(i in allpoints){
    dataPoints.add(DataPoint(i.date, i.weight.toDouble()))
}

        series.resetData(dataPoints.toTypedArray())



        super.onResume()

        val graph = getView()?.findViewById<GraphView >(R.id.graph)// as GraphView



        graph?.addSeries(series)

        graph?.getGridLabelRenderer()?.setLabelFormatter(DateAsXAxisLabelFormatter(getActivity()));
        graph?.getGridLabelRenderer()?.setNumHorizontalLabels(4);

        graph?.getViewport()?.setScrollable(true);
        graph?.getViewport()?.setScrollableY(true);
        graph?.getViewport()?.setScalableY(true);

        graph?.getViewport()?.setYAxisBoundsManual(true);
        graph?.getViewport()?.setMinY(0.0);
        graph?.getViewport()?.setMaxY(200.0);


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}