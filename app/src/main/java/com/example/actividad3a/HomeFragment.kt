package com.example.actividad3a

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    val args: HomeFragmentArgs by navArgs()
    var datos: RegisterFragment.User? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = true
        var bottomNav = activity?.findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNav.isVisible = true
        var genre_content_list = listOf(genre_content("https://cdn-icons-png.flaticon.com/512/2790/2790402.png", "Aventura"), genre_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Acción"), genre_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "Arcade")
        , genre_content("https://cdn1.iconfinder.com/data/icons/game-design-butterscotch-vol-2/256/Sports_Game-1024.png", "Deportes"), genre_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Estrategia"))
        datos = args.user
        val mAdapter = HomeAdapter(genre_content_list) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }
        val mainRecyclerView: RecyclerView = binding.genreRecyclerView
        mainRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mainRecyclerView.adapter = mAdapter
    }
}