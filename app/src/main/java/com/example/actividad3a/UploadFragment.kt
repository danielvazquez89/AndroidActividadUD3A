package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentUploadBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Subir")
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
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
        var genre_content_list = listOf(genre_content("https://cdn-icons-png.flaticon.com/512/2790/2790402.png", "Aventura"), genre_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Acción"), genre_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "Arcade")
            , genre_content("https://cdn1.iconfinder.com/data/icons/game-design-butterscotch-vol-2/256/Sports_Game-1024.png", "Deportes"), genre_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Estrategia"))
        val mAdapter = UploadAdapter(genre_content_list) {
            val directions = UploadFragmentDirections.actionUploadFragmentToUploadDetailsFragment(it.nombreGenero)
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        val mainRecyclerView: RecyclerView = binding.genreUploadRecyclerView
        mainRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mainRecyclerView.adapter = mAdapter
    }
}