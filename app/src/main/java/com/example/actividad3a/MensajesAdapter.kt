package com.example.actividad3a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.MensajesChatResponse


class MensajesAdapter(private val mDataSet: ArrayList<MensajesChatResponse.MensajesChatResponseItem>) :
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
        val tvMensajePorUsario = v.findViewById<TextView>(R.id.tvMensajePorUsario)
        val tvHoraMensajeEnviado = v.findViewById<TextView>(R.id.tvHoraMensajeEnviado)


        fun bindItems(data: MensajesChatResponse.MensajesChatResponseItem) {

            //tvHoraMensajeEnviado.text = data.
            tvMensajePorUsario.text = data.contenidoMensaje

        }
    }
}