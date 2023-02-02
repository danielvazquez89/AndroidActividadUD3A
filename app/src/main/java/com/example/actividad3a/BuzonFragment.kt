package com.example.actividad3a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentBuzonBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BuzonFragment : Fragment() {
    private var _binding: FragmentBuzonBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuzonBinding.inflate(inflater, container, false)
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
        var genre_content_list = listOf(genre_content("https://e7.pngegg.com/pngimages/807/26/png-clipart-envelope-mail-icon-envelope-miscellaneous-angle.png", "Pepito"), genre_content("https://e7.pngegg.com/pngimages/807/26/png-clipart-envelope-mail-icon-envelope-miscellaneous-angle.png", "Juanito"), genre_content("https://e7.pngegg.com/pngimages/807/26/png-clipart-envelope-mail-icon-envelope-miscellaneous-angle.png", "Paquito")
            , genre_content("https://e7.pngegg.com/pngimages/807/26/png-clipart-envelope-mail-icon-envelope-miscellaneous-angle.png", "Antonio V."), genre_content("https://e7.pngegg.com/pngimages/807/26/png-clipart-envelope-mail-icon-envelope-miscellaneous-angle.png", "Pedro P."))
        val mAdapter = BuzonAdapter(genre_content_list) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }
        val mainRecyclerView: RecyclerView = binding.chatsRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = mAdapter
    }
}