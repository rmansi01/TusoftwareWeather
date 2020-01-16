package net.azarquiel.tusoftwareweather.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rowweather.view.*
import net.azarquiel.tusoftwareweather.model.Data
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by pacopulido on 9/10/18.
 */
class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Data> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setDaily(dailys: List<Data>) {
        this.dataList = dailys
        notifyDataSetChanged()
    }




    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Data){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem


            itemView.tvrowmax.text = "${((dataItem.temperatureMax - 39)*5/9).toInt()} ºC" // Pintamos la temperatura maxima en el daily
            itemView.tvrowmin.text = "${((dataItem.temperatureMin - 39)*5/9).toInt()} ºC" // Pintamos la temperatura minima en el daily
            itemView.tvrowdesc.text = "${dataItem.summary}" // Pintamos la descripcion del tiempo
            itemView.tvrowdate.text = "${(dataItem.time)}" // Añadimos la fecha en cada fila del RecycledView
            itemView.tvrowpp.text = "${(dataItem.precipProbability * 100).toInt()}%" // Pintamos la probabilidad de precipitaciones.

           /* val id = context.resources.getIdentifier(dataItem.foto,"drawable",context.packageName)
            itemView.ivrow.setImageResource(id)
            itemView.tag = dataItem*/
            // Si la foto viene de Internet
            var foto = "https://darksky.net/images/weather-icons/${dataItem.icon}.png"
            Picasso.get().load(foto).into(itemView.ivrow)
            itemView.tag = dataItem
        }

        private fun formatFecha(time:Long) : String {
            val fecha = Date(time)
            val format = SimpleDateFormat("dd/MM")
            return format.format(fecha)
        }

        /* private fun ponfecha(time: Long) {
             val sdf = SimpleDateFormat("dd/MM/yyyy")
             return sdf.format(time)
         }*/

    }
}