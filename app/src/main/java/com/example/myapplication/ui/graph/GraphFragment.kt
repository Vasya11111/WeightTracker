package com.example.myapplication.ui.graph






import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Database
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGraphBinding
import com.example.myapplication.ui.notifications.GraphViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*


class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null





    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
                inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val graphViewModel =
            ViewModelProvider(this).get(GraphViewModel::class.java)

        _binding = FragmentGraphBinding.inflate(inflater, container, false)
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

        """val graph = getView()?.findViewById<GraphView >(R.id.graph)// as GraphView



        graph?.addSeries(series)

        graph?.getGridLabelRenderer()?.setLabelFormatter(DateAsXAxisLabelFormatter(getActivity()));
        graph?.getGridLabelRenderer()?.setNumHorizontalLabels(4);

        graph?.getViewport()?.setScrollable(true);
        graph?.getViewport()?.setScrollableY(true);
        graph?.getViewport()?.setScalableY(true);

        graph?.getViewport()?.setYAxisBoundsManual(true);
        graph?.getViewport()?.setMinY(0.0);
        graph?.getViewport()?.setMaxY(200.0);"""

        val chart = requireView().findViewById(R.id.chart) as LineChart

        val entries: ArrayList<Entry> = ArrayList<Entry>()

        val xAxis: XAxis = chart.getXAxis()
        val formatter: ValueFormatter = object : ValueFormatter() {
            private val mFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return mFormat.format(Date(value.toLong()))
            }
        }

        xAxis.valueFormatter = formatter

        val dateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)

        for(i in allpoints){

            entries.add(Entry(i.date.time.toFloat(), i.weight.toFloat()))
        }


       // entries.add(Entry(dateFormat.parse("01 Jul").time.toFloat(), 100f))
       // entries.add(Entry(dateFormat.parse("02 Jul").time.toFloat(), 120f))
        //entries.add(Entry(dateFormat.parse("03 Jul").time.toFloat(), 130f))
        val dataSet = LineDataSet(entries, "Вес КГ")
        val lineData = LineData(dataSet)
        chart.data = lineData


        val ll1 = LimitLine(80f, "Upper Limit")
        ll1.lineWidth = 4f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll1.textSize = 10f
        ll1.lineColor=R.color.red



        val ll2 = LimitLine(85f, "Upper Limit")
        ll2.lineWidth = 4f
        ll2.enableDashedLine(10f, 10f, 0f)
        ll2.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll2.textSize = 10f


        val yAxis: YAxis = chart.getAxis(YAxis.AxisDependency.LEFT)
        yAxis.addLimitLine(ll1)
        yAxis.addLimitLine(ll2)




        chart.invalidate() // refresh


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}