package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.GenerosResponse
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.models.UserRequest
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    val args: HomeFragmentArgs by navArgs()
    var datos: UserRequest? = null
    private var _binding: FragmentHomeBinding? = null
    val TAG = "HomeFragment"
    private var adapterGeneros: HomeAdapter? = null
    private var adapterJuegos: HomeGamesAdapter? = null
    var dataGeneros: ArrayList<GenerosResponse.GenerosResponseItem> = ArrayList()
    var dataJuegos: ArrayList<JuegosResponse.JuegosResponseItem> = ArrayList()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Home")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


/*  val btnHeart = view.findViewById<ImageView>(R.id.corazon)
        btnHeart.setOnClickListener {
            btnHeart.setImageResource(R.drawable.ic_baseline_favorite_24); //replace image

        }


*/

        var bottomNav = activity?.findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNav.isVisible = true
/*
        var genre_content_list = listOf(
            genre_content("https://cdn-icons-png.flaticon.com/512/2790/2790402.png", "Aventura"),
            genre_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Acción"),
            genre_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "Arcade"),
            genre_content(
                "https://cdn1.iconfinder.com/data/icons/game-design-butterscotch-vol-2/256/Sports_Game-1024.png",
                "Deportes"
            ),
            genre_content(
                "https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg",
                "Estrategia"
            )
        )

        var game_content_list = listOf(
            game_content(
                "https://m.media-amazon.com/images/I/815ng-+eeSL._AC_SL1500_.jpg",
                "Last of Us"
            ),
            game_content(
                "https://m.media-amazon.com/images/I/81Pagnfx1DL._SL1500_.jpg",
                "Rayman 3"
            ),
            game_content(
                "https://m.media-amazon.com/images/I/71ApMdd+7bL._SL1361_.jpg",
                "NintenDogs"
            ),
            game_content(
                "https://m.media-amazon.com/images/I/81vJ+ekX1BL._AC_SL1500_.jpg",
                "Wiisports"
            ),
            game_content("https://m.media-amazon.com/images/I/81RxV8YQdRL._SL1500_.jpg", "Loney")
        )
        */

        datos = args.user

        getGeneros()

        adapterGeneros = HomeAdapter(dataGeneros) {

            val directions = HomeFragmentDirections.actionHomeFragmentToGamesByGenreFragment(it.tipoGenero)
            findNavController().navigate(directions)

            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }

        getJuegos()

        adapterJuegos = HomeGamesAdapter(dataJuegos) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
            val directions = HomeFragmentDirections.actionHomeFragmentToGameDescriptionFragment(it)
            findNavController().navigate(directions)
        }

        val mainRecyclerView: RecyclerView = binding.genreRecyclerView
        mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mainRecyclerView.adapter = adapterGeneros


        val secundaryRecyclerView: RecyclerView = binding.gamesRecyclerView
        secundaryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        secundaryRecyclerView.adapter = adapterJuegos

    }

    private fun getGeneros() {
        val call = ApiRest.service.getGeneros()
        call.enqueue(object : Callback<GenerosResponse> {
            override fun onResponse(
                call: Call<GenerosResponse>,
                response: Response<GenerosResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataGeneros.clear()
                    dataGeneros.addAll(body)
                    adapterGeneros?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<GenerosResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    private fun getJuegos() {
        val call = ApiRest.service.getJuegos()
        call.enqueue(object : Callback<JuegosResponse> {
            override fun onResponse(
                call: Call<JuegosResponse>,
                response: Response<JuegosResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataJuegos.clear()
                    dataJuegos.addAll(body)
                    adapterJuegos?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<JuegosResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

}