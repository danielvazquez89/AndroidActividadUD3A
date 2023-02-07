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


class UploadWithCategoryAdapter  (private val mDataSet: List<upload_with_category_content>) :
    RecyclerView.Adapter<UploadWithCategoryAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.upload_with_category_list, parent, false)
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
     //   val foto = v.findViewById<ImageView>(R.id.tvTitle)
        val title = v.findViewById<TextView>(R.id.tvTitle)
        val descripcion = v.findViewById<TextView>(R.id.tvDescription)
        val category = v.findViewById<TextView>(R.id.tvCategory)
        val precio = v.findViewById<TextView>(R.id.tvPrecio)

        fun bindItems(data: upload_with_category_content) {
            //foto.text = data.fechaMensaje
            title.text = data.title
            descripcion.text = data.descripcion
            category.text = data.category
            precio.text = data.precio
        }
    }
}