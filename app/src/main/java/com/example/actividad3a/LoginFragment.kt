package com.example.actividad3a

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.actividad3a.data.models.UserRequest
import com.example.actividad3a.databinding.FragmentLoginBinding
import com.example.navigationcomponent.Validaciones
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Log In")
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        setupActivityLink()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        binding.textViewIniciarSesion.setOnClickListener {
            binding.textFieldEmail.editText?.setText("email@gmail.com")
            binding.textFieldContrasena.editText?.setText("contraseña1234")
        }
//        Log.i("RegisterFragment", args.name)
        binding.btLogin.setOnClickListener {
            val objetoValidaciones = Validaciones()
            val emailValido = objetoValidaciones.esEmailValido(binding.textFieldEmail)
            val validoContrasena =
                objetoValidaciones.esContrasenaValida(binding.textFieldContrasena)
            if (emailValido && validoContrasena) {
                //Iniciar sesion
                val email = binding.textFieldEmail.editText?.text.toString()
                //val contrasena = binding.textFieldContrasena.editText?.text.toString()
                val user = UserRequest("", email, "", "", "", "", "", "", null, null)
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(user)
                findNavController().navigate(action)
            }
        }
    }

    fun setupActivityLink() {
        val linkTextView = binding.irRegistrarse
        linkTextView.setLinkTextColor(Color.BLUE)
        linkTextView.setOnClickListener {
            findNavController().navigate( com.example.actividad3a.R.id.action_loginFragment_to_registerFragment)
        }
    }

}