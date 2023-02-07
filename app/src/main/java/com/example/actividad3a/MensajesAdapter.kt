package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MensajesAdapter (private val mDataSet: List<message_content>) :
    RecyclerView.Adapter<MensajesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mensaje_list, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        data.let { holder.bindItems(it) }
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val tvHoraMensajeEnviado = v.findViewById<TextView>(R.id.tvMensajePorUsario)
        val tvMensajePorUsario = v.findViewById<TextView>(R.id.tvHoraMensajeEnviado)


        fun bindItems(data: message_content) {

            tvHoraMensajeEnviado.text = data.fechaMensaje
            tvMensajePorUsario.text = data.textoMensaje

        }
    }
}