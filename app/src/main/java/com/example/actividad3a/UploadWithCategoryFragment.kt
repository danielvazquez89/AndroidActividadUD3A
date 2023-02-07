package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.databinding.FragmentBuzonBinding
import com.example.actividad3a.databinding.FragmentConfiguracionBinding
import com.example.actividad3a.databinding.FragmentUploadWithCategoryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class UploadWithCategoryFragment : Fragment() {

    private var _binding: FragmentUploadWithCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadWithCategoryBinding.inflate(inflater, container, false)
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

        var genre_content_list = listOf(upload_with_category_content("", "Hamtaro","gran jeugo gran persona","Drama","89"))


        val mAdapter = UploadWithCategoryAdapter(genre_content_list) /*{
          //  val directions = BuzonFragmentDirections.actionBuzonFragmentToMensajesFragment()
        //    findNavController().navigate(directions)
        }*/

        val mainRecyclerView: RecyclerView = binding.uploadWihRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = mAdapter

        val btUploadtoHome = view.findViewById<Button>(R.id.btUploadtoHome)
        btUploadtoHome.setOnClickListener {
            Log.i("Pepa", "subido productoa")
            val action = UploadWithCategoryFragmentDirections.actionUploadWithCategoryFragmentToTuFragment()
            findNavController().navigate(action)
        }
    }
}