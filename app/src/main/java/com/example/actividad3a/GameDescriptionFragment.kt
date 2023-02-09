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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.actividad3a.databinding.FragmentGameDescriptionBinding
import com.example.actividad3a.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class GameDescriptionFragment : Fragment() {
    val args: GameDescriptionFragmentArgs by navArgs()
    private var _binding: FragmentGameDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Descripción juego")
        _binding = FragmentGameDescriptionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false

        binding.miDescripcionJuego.text = args.juego.descripcionJuego
        Glide.with(binding.imagenJuego.context).load(args.juego.urlImagen).into(binding.imagenJuego)
        binding.miPrecioJuego.text = args.juego.precio.toString()
        binding.miTituloJuego.text = args.juego.nombreJuego

        val btAddGameToHome = view.findViewById<Button>(R.id.btAddGameToHome)
        btAddGameToHome.setOnClickListener {
            Log.i("Pepa", "ebtra ya")
            val action = GameDescriptionFragmentDirections.actionGameDescriptionFragmentToTuFragment()
            findNavController().navigate(action)
        }

        val tvNameUsertoPerfileUser = view.findViewById<TextView>(R.id.miNombreVendedor)
        tvNameUsertoPerfileUser.setOnClickListener {
            Log.i("Pepa", "vas a usuario perfil")
            val action = GameDescriptionFragmentDirections.actionGameDescriptionFragmentToPerfilUsuarioFragment()
            findNavController().navigate(action)
        }

    }
}