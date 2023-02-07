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
import com.example.actividad3a.databinding.FragmentComprasBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ComprasFragment : Fragment() {

    private var _binding: FragmentComprasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComprasBinding.inflate(inflater, container, false)
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

        var genre_content_list = listOf(compra_content("","Mortadelo","Pepe","11 enero","10€"))

        val mAdapter = ComprasAdapter(genre_content_list) /*{
          //  val directions = BuzonFragmentDirections.actionBuzonFragmentToMensajesFragment()
            //findNavController().navigate(directions)
        }*/

        val mainRecyclerView: RecyclerView = binding.comprasRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = mAdapter
    }
}