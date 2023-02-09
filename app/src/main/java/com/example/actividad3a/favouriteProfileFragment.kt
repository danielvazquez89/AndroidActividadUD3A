package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.JuegosResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentFavouriteProfileBinding
import com.example.actividad3a.databinding.FragmentGamesByGenreBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class favouriteProfileFragment : Fragment() {

    private var _binding: FragmentFavouriteProfileBinding? = null
    private val binding get() = _binding!!

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

        var profile_favorite_content_list = listOf(
            user_content("https://cdn0.psicologia-online.com/es/posts/2/4/2/que_piensa_una_persona_cuando_dejas_de_buscarla_5242_600_square.jpg", "Last of Us"), user_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Rayman 3"), user_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "NintenDogs")
            , user_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Wiisports"), user_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Loney")
        )

        val mAdapter = FavoriteProfileAdapter(profile_favorite_content_list) {

        }

        val mainRecyclerView: RecyclerView = binding.favoriteProfileRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mainRecyclerView.adapter = mAdapter

    }



}