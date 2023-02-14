package com.example.actividad3a

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.*
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentFavouriteProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class favouriteProfileFragment : Fragment() {

    private var _binding: FragmentFavouriteProfileBinding? = null
    private val binding get() = _binding!!
    private var adapterUsuarios: FavoriteProfileAdapter? = null
    var dataUsuarios: ArrayList<UsersResponse.UsersResponseItem> = ArrayList()
    var dataUsuariosFavoritos: ArrayList<UsuariosFavoritosResponse.UsuariosFavoritosResponseItem> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     //   activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
/*
        var profile_favorite_content_list = listOf(
            user_content("https://cdn0.psicologia-online.com/es/posts/2/4/2/que_piensa_una_persona_cuando_dejas_de_buscarla_5242_600_square.jpg", "Last of Us"), user_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Rayman 3"), user_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "NintenDogs")
            , user_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Wiisports"), user_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Loney")
        )
 */

        var userId = 0
        Preferences.getUserId()?.let {
            Log.i("MainActivity", it)
            userId = it.toInt()
        }
        if (userId != null) {
            getUsuariosFavoritosByUserId(userId)
        }

        adapterUsuarios = FavoriteProfileAdapter(dataUsuarios) {

        }

        val mainRecyclerView: RecyclerView = binding.favoriteProfileRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mainRecyclerView.adapter = adapterUsuarios

    }

    private fun getUsuariosFavoritosByUserId(id: Int) {
        val call = ApiRest.service.getUsuariosFavoritosByUserId(id)
        call.enqueue(object : Callback<UsuariosFavoritosResponse> {
            override fun onResponse(
                call: Call<UsuariosFavoritosResponse>,
                response: Response<UsuariosFavoritosResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataUsuariosFavoritos.clear()
                    dataUsuariosFavoritos.addAll(body)
                    // Get juegos by id aquí
                    for (Item: UsuariosFavoritosResponse.UsuariosFavoritosResponseItem in dataUsuariosFavoritos) {
                        getUsuarioFavoritoByUserId(Item.idVendedor)
                    }
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<UsuariosFavoritosResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    private fun getUsuarioFavoritoByUserId(id: Int) {
        val call = ApiRest.service.getUserById(id)
        call.enqueue(object : Callback<UsersResponse.UsersResponseItem> {
            override fun onResponse(
                call: Call<UsersResponse.UsersResponseItem>,
                response: Response<UsersResponse.UsersResponseItem>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataUsuarios.add(body)
                    adapterUsuarios?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<UsersResponse.UsersResponseItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }



}