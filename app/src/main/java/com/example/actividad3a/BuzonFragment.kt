package com.example.actividad3a

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.*
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentBuzonBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuzonFragment : Fragment() {
    private var _binding: FragmentBuzonBinding? = null
    private val binding get() = _binding!!
    var dataJuegos: ArrayList<JuegosResponse.JuegosResponseItem> = ArrayList()
    private var adapterChats: BuzonAdapter? = null
    var dataChats: ArrayList<ChatResponse.ChatResponseItem> = ArrayList()
   // var dataUsuariosFavoritos: ArrayList<UsuariosFavoritosResponse.UsuariosFavoritosResponseItem> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuzonBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = true

        //var genre_content_list = listOf(chat_content("", "14 enero","Last of uS","Donde estas?"))
        var userId = 0
        Preferences.getUserId()?.let {
            Log.i("MainActivity", it)
            userId = it.toInt()
        }
        getChatsByUserId(userId)
        adapterChats = BuzonAdapter(dataChats) {
            val directions = BuzonFragmentDirections.actionBuzonFragmentToMensajesFragment(it.idChat)
            findNavController().navigate(directions)
        }

        val mainRecyclerView: RecyclerView = binding.chatsRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = adapterChats
    }

    private fun getChatsByUserId(id: Int) {
        val call = ApiRest.service.getChatsUser(id)
        call.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(
                call: Call<ChatResponse>,
                response: Response<ChatResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataChats.clear()
                    dataChats.addAll(body)
                    adapterChats?.notifyDataSetChanged()
                    // Get juegos by id aquí
                   // for (Item: ChatResponse.ChatResponseItem in dataChats) {
                     //   getJuegoById(Item.idProducto)
                    //}
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }



}