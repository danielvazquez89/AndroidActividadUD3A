package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var genre_content_list = listOf(genre_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Aventura"), genre_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Acción"), genre_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "Arcade")
            , genre_content("https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg", "Deportes"), genre_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Estrategia"))
        val mAdapter = HomeAdapter(genre_content_list) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }
        val mainRecyclerView: RecyclerView = binding.genreRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 2)

        mainRecyclerView.adapter = mAdapter
    }
}