package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentGamesByGenreBinding
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesByGenreFragment : Fragment() {

    private var _binding: FragmentGamesByGenreBinding? = null
    private val binding get() = _binding!!
    val TAG = "Juegos por género"
    private var adapterJuegos: GamesByGenreAdapter? = null
    var dataJuegos: ArrayList<JuegosResponse.JuegosResponseItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Juegos por género")
        _binding = FragmentGamesByGenreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false

       var game_content_list = listOf(
           game_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Last of Us"), game_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Rayman 3"), game_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "NintenDogs")
            , game_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Wiisports"), game_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Loney")
       )

        getJuegosPorGenero()

        adapterJuegos = GamesByGenreAdapter(dataJuegos) {
            val directions = GamesByGenreFragmentDirections.actionGamesByGenreFragmentToGameDescriptionFragment(it)
            findNavController().navigate(directions)
        }

        val mainRecyclerView: RecyclerView = binding.gamesRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mainRecyclerView.adapter = adapterJuegos

    }

    private fun getJuegosPorGenero() {
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