package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.GenerosResponse
import com.example.actividad3a.data.models.JuegosFavoritosResponse
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.models.Preferences
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentFavouriteProductsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class favouriteProductsFragment : Fragment() {

    private var _binding: FragmentFavouriteProductsBinding? = null
    private val binding get() = _binding!!
    val TAG = "Favourite Products"
    private var adapterJuegos: FavoriteGameAdapter? = null
    var dataJuegos: ArrayList<JuegosResponse.JuegosResponseItem> = ArrayList()
    var dataJuegosFavoritosUsuario: ArrayList<JuegosFavoritosResponse.JuegosFavoritosResponseItem> =
        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
/*
        var game_favorite_content_list = listOf(
            game_content(
                "https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg",
                "Last of Us"
            ),
            game_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Rayman 3"),
            game_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "NintenDogs"),
            game_content(
                "https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg",
                "Wiisports"
            ),
            game_content(
                "https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg",
                "Loney"
            )
        )

 */
        var userId = 0
        Preferences.getUserId()?.let {
            Log.i("MainActivity", it)
            userId = it.toInt()
        }
        if (userId != null) {
            getJuegosFavoritosByUserId(userId)
        }


        adapterJuegos = FavoriteGameAdapter(dataJuegos) {
            //val directions = .actionHomeFragmentToGameDescriptionFragment(it)
            //findNavController().navigate(directions)
        }

        val mainRecyclerView: RecyclerView = binding.favoriteGamesRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mainRecyclerView.adapter = adapterJuegos

    }

    private fun getJuegosFavoritosByUserId(id: Int) {
        val call = ApiRest.service.getJuegosFavoritosById(id)
        call.enqueue(object : Callback<JuegosFavoritosResponse> {
            override fun onResponse(
                call: Call<JuegosFavoritosResponse>,
                response: Response<JuegosFavoritosResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataJuegosFavoritosUsuario.clear()
                    dataJuegosFavoritosUsuario.addAll(body)
                    // Get juegos by id aquí
                    for (Item: JuegosFavoritosResponse.JuegosFavoritosResponseItem in dataJuegosFavoritosUsuario) {
                        getJuegoById(Item.idProducto)
                    }
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<JuegosFavoritosResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }


    private fun getJuegoById(id: Int) {
        val call = ApiRest.service.getJuegoById(id)
        call.enqueue(object : Callback<JuegosResponse.JuegosResponseItem> {
            override fun onResponse(
                call: Call<JuegosResponse.JuegosResponseItem>,
                response: Response<JuegosResponse.JuegosResponseItem>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataJuegos.add(body)
                    adapterJuegos?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<JuegosResponse.JuegosResponseItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }


}