package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

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

        val btAddGameToHome = view.findViewById<Button>(R.id.btAddGameToHome)
        btAddGameToHome.setOnClickListener {
            Log.i("Pepa", "ebtra ya")
            val action = GameDescriptionFragmentDirections.actionGameDescriptionFragmentToTuFragment()
            findNavController().navigate(action)
        }
    }
}