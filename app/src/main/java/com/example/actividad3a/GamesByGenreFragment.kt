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
import com.example.actividad3a.databinding.FragmentGamesByGenreBinding
import com.example.actividad3a.GamesByGenreFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class GamesByGenreFragment : Fragment() {

    private var _binding: FragmentGamesByGenreBinding? = null
    private val binding get() = _binding!!

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


        val mAdapter = GamesByGenreAdapter(game_content_list) {
            val directions = com.example.actividad3a.GamesByGenreFragmentDirections.actionGamesByGenreFragmentToGameDescriptionFragment()
            findNavController().navigate(directions)
        }

        val mainRecyclerView: RecyclerView = binding.gamesRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mainRecyclerView.adapter = mAdapter

    }

}