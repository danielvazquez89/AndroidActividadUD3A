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


class VentasAdapter(private val mDataSet: List<venta_content>) :
    RecyclerView.Adapter<VentasAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ventas_list, parent, false)
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
        val miImagenVentaJuego = v.findViewById<ImageView>(R.id.miImagenVentaJuego)
        val UsuarioVendedor = v.findViewById<TextView>(R.id.UsuarioVendedor)
        val miNombreJuegoVenta = v.findViewById<TextView>(R.id.miNombreJuegoVenta)
        val fechaVenta = v.findViewById<TextView>(R.id.fechaVenta)
        val textoPrecio = v.findViewById<TextView>(R.id.precioVenta)

        fun bindItems(data: venta_content) {
            Glide.with(miImagenVentaJuego.context).load(data.fotoJuego).into(miImagenVentaJuego)
            UsuarioVendedor.text = data.textoComprador
            miNombreJuegoVenta.text = data.textoProducto
            fechaVenta.text = data.textoFecha
            textoPrecio.text = data.textoPrecio

        }
    }
}