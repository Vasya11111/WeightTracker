package com.example.myapplication








import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.series.DataPoint
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_history, R.id.navigation_graph, R.id.navigation_notes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)






        val arguments = getIntent().getExtras()


        if(arguments!=null) {
            val t=arguments.getInt("name")
            if(t==1) {
                val navController = findNavController(R.id.nav_host_fragment_activity_main)
                val graph = navController.navInflater.inflate(R.navigation.mobile_navigation)
                graph.setStartDestination(R.id.navigation_history);
                navController.setGraph(graph, null)
            }else{
                val navController = findNavController(R.id.nav_host_fragment_activity_main)
                val graph = navController.navInflater.inflate(R.navigation.mobile_navigation)
                graph.setStartDestination(R.id.navigation_notes);
                navController.setGraph(graph, null)
            }

        }

        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        val datab= Database(db)
        datab.createTables(this)

            // datab.insertDots()

        }




    fun addTodayMeasure(view: View?) {


        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)


        val picker : NumberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        val pickerVal =picker.value

        val datab= Database(db)


        val currentDate = Calendar.getInstance()
        currentDate.time = Date()
        datab.insertData(currentDate,pickerVal)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", 1);

        startActivity(intent)

       // val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
      //  val datab= Database(db)
       // datab.insertGoal(75,"2 года назад")
       // datab.insertGoal(70,"нормально")


    }


    fun onClickAdd(view: View?){

        val intent = Intent(this, Add_measure::class.java)
        startActivity(intent)

    }


    fun onClickAddNote(view: View?){

        val intent = Intent(this, Add_note::class.java)
        startActivity(intent)

    }

    fun showButton(view: View?){

       val button=view?.findViewById<Button>(R.id.buttonDelete)
        button?.visibility=VISIBLE

    }

    fun deleteMeasure(view: View?){

        val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale("ru"))



        val viewRelative = view?.parent as ViewGroup
        val child:TextView = viewRelative.getChildAt(0) as TextView
        var text:String = child.text.toString()
        text=text.substring(0,text.length-3)


          var date = dateFormatter.parse(text)

        val currentDate = Calendar.getInstance()
        currentDate.time = date

        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)

        val datab= Database(db)

        datab.deleteDate(currentDate)

        var viewCard = viewRelative?.parent as ViewGroup
        val viewLinear = viewCard?.parent as ViewGroup
        viewLinear.setPadding(0,0,0,0)
        viewCard.visibility=GONE


    }

/*fun exp(view: View?) {


   val navController = findNavController(R.id.nav_host_fragment_activity_main)
    navController.navigate(R.id.navigation_dashboard);*/


   /* val graph = navController.navInflater.inflate(R.navigation.mobile_navigation)
    graph.setStartDestination(R.id.navigation_dashboard);
    navController.setGraph(graph,null)
}*/


}