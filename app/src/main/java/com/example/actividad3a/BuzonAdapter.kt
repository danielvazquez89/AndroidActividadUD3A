package com.example.actividad3a

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actividad3a.data.models.ChatResponse
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.remotes.ApiRest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuzonAdapter(private val mDataSet: ArrayList<ChatResponse.ChatResponseItem>, var onClick: (ChatResponse.ChatResponseItem) -> Unit) :
    RecyclerView.Adapter<BuzonAdapter.MainViewHolder>() {
    var dataJuego: JuegosResponse.JuegosResponseItem? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mesage_list, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        data.let { holder.bindItems(it) }
        holder.itemView.setOnClickListener {
            onClick(data)
            //if (data)
            //  holder.mytexto.text = "\uFEFF\uD83D\uDCA5\uFEFF"
            //else
            //  holder.mytexto.text = "\uFEFF\uD83D\uDEA9\uFEFF "
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val miFotoJuego = v.findViewById<ImageView>(R.id.miImagenJuego)
        val miFechaMensaje = v.findViewById<TextView>(R.id.miFechaReceptor)
        val miNombreoJuego = v.findViewById<TextView>(R.id.miNombreJuegoChat)
        val miNombreReceptor = v.findViewById<TextView>(R.id.miMensajePreview)

        fun bindItems(data: ChatResponse.ChatResponseItem) {
            //Glide.with(miFotoJuego.context).load(data.fotoJuego).into(miFotoJuego)
            //miFechaMensaje.text = data.fechaChat
            //miNombreoJuego.text = data.nombreProducto
            miNombreReceptor.text = "Pepito"
            getJuegoById(data.idProducto, v)
        }
    }

    private fun getJuegoById(id: Int, v: View) {
        val call = ApiRest.service.getJuegoById(id)
        call.enqueue(object : Callback<JuegosResponse.JuegosResponseItem> {
            override fun onResponse(
                call: Call<JuegosResponse.JuegosResponseItem>,
                response: Response<JuegosResponse.JuegosResponseItem>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(ContentValues.TAG, body.toString())
                    dataJuego = body
                    Glide.with(v.findViewById<ImageView>(R.id.miImagenJuego).context).load(dataJuego?.urlImagen).into(v.findViewById<ImageView>(R.id.miImagenJuego))
                    v.findViewById<TextView>(R.id.miNombreJuegoChat).text = dataJuego?.nombreJuego
                    //val miNombreReceptor = v.findViewById<TextView>(R.id.miNombreVendedor).text = dataJuego
                //adapterJuegos?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(ContentValues.TAG, it) }
                }
            }

            override fun onFailure(call: Call<JuegosResponse.JuegosResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, t.message.toString())
            }
        })
    }
}