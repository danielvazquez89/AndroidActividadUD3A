package com.example.actividad3a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BuzonAdapter(private val mDataSet: List<chat_content>, var onClick: (chat_content) -> Unit) :
    RecyclerView.Adapter<BuzonAdapter.MainViewHolder>() {
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

        fun bindItems(data: chat_content) {
            Glide.with(miFotoJuego.context).load(data.fotoJuego).into(miFotoJuego)
            miFechaMensaje.text = data.fechaChat
            miNombreoJuego.text = data.nombreProducto
            miNombreReceptor.text = data.previewMensaje

        }
    }
}