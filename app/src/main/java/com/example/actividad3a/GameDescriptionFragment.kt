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
import com.google.android.material.bottomnavigation.BottomNavigationView

class GameDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false

        val btAddGameToHome = view.findViewById<Button>(R.id.btAddGameToHome)
        btAddGameToHome.setOnClickListener {
            Log.i("Pepa", "ebtra ya")
            val action = GameDescriptionFragmentDirections.actionGameDescriptionFragmentToTuFragment()
            findNavController().navigate(action)
        }

        val tvNameUsertoPerfileUser = view.findViewById<TextView>(R.id.tvSeller)
        tvNameUsertoPerfileUser.setOnClickListener {
            Log.i("Pepa", "vas a usuario perfil")
            val action = GameDescriptionFragmentDirections.actionGameDescriptionFragmentToPerfilUsuarioFragment()
            findNavController().navigate(action)
        }

    }
}