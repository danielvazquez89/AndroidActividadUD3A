package com.example.actividad3a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.actividad3a.data.models.UsersResponse


class FavoriteProfileAdapter(private val mDataSet: ArrayList<UsersResponse.UsersResponseItem>, var onClick: (UsersResponse.UsersResponseItem) -> Unit) :
    RecyclerView.Adapter<FavoriteProfileAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_profile_list, parent, false)
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
        val mytexto = v.findViewById<TextView>(R.id.miNombreUsuario)
        val miFoto = v.findViewById<ImageView>(R.id.imagenUsuario)
        fun bindItems(data: UsersResponse.UsersResponseItem) {
            //mytexto.text = data
            mytexto.text = data.nombre
            //Glide.with(miFoto.context).load("https://cdn-icons-png.flaticon.com/512/64/64572.png").into(miFoto)
        }
    } }