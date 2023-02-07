package com.example.actividad3a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class GamesByGenreAdapter(private val mDataSet: List<game_content>, var onClick: (game_content) -> Unit) :
    RecyclerView.Adapter<GamesByGenreAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.all_game_by_genre_list, parent, false)
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
        val mytexto = v.findViewById<TextView>(R.id.miTextoJuego)
        val miFoto = v.findViewById<ImageView>(R.id.imagenJuego)
        fun bindItems(data: game_content) {
            //mytexto.text = data
            mytexto.text = data.nombreJuego
            Glide.with(miFoto.context).load(data.fotoJuego).into(miFoto)
        }
    }
}