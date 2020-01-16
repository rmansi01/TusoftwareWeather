package net.azarquiel.tusoftwareweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.tusoftwareweather.adapters.CustomAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import net.azarquiel.tusoftwareweather.model.Result

class MainActivity : AppCompatActivity() {
    private lateinit var result: Result
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        doAsync {
            val jsontxt = URL("https://api.darksky.net/forecast/21259f9de3537b4f719c53580fa39c3a/39.8710026,-4.0251675?lang=es&exclude=minutely,hourly,alerts,flags").readText()
            result = Gson().fromJson(jsontxt,Result::class.java)
            uiThread {
                result.daily.data.forEach(){
                    Log.d("tusoftware",it.toString())
                }
                showData()
            }
        }
    }

    private fun showData() {
        adapter = CustomAdapter(this, R.layout.rowweather)
        rvweather.adapter = adapter
        rvweather.layoutManager = LinearLayoutManager(this)
        adapter.setDaily(result.daily.data)
    }
}
