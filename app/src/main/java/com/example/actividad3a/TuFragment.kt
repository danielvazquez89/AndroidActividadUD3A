package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentTuBinding

class TuFragment : Fragment() {
    private var _binding: FragmentTuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var genre_content_list = listOf(genre_content("https://www.iconpacks.net/icons/2/free-icon-fast-buy-3048.png", "Compras"), genre_content("https://www.clipartmax.com/png/middle/147-1477041_free-sales-icons-sales-icon.png", "Ventas"), genre_content("https://cdn-icons-png.flaticon.com/512/3359/3359235.png", "Monedero")
            , genre_content("https://cdn-icons-png.flaticon.com/512/1170/1170686.png", "Configuración"), genre_content("https://cdn0.iconfinder.com/data/icons/cosmo-symbols/40/help_1-1024.png", "Ayuda"))
        val mAdapter = HomeAdapter(genre_content_list) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }
        val mainRecyclerView: RecyclerView = binding.genreRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = mAdapter
    }
}