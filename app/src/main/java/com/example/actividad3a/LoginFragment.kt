package com.example.actividad3a

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.actividad3a.data.models.*
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentLoginBinding
import com.example.navigationcomponent.Validaciones
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var dataUser: UsersResponse.UsersResponseItem? = null

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
            binding.textFieldEmail.editText?.setText("pepito@gmail.com")
            binding.textFieldContrasena.editText?.setText("123456")
        }
//        Log.i("RegisterFragment", args.name)
        binding.btLogin.setOnClickListener {
            //val objetoValidaciones = Validaciones()
            //val emailValido = objetoValidaciones.esEmailValido(binding.textFieldEmail)
            //val validoContrasena =
            //objetoValidaciones.esContrasenaValida(binding.textFieldContrasena)
            //if (emailValido && validoContrasena) {
            //Iniciar sesion
            val email = binding.textFieldEmail.editText?.text.toString()
            val contrasena = binding.textFieldContrasena.editText?.text.toString()
            val user = UserRequest("", email, "", contrasena, "", "", "", "", null, null)
            getUserByEmail(email, user)

        }
        //}
    }

    fun setupActivityLink() {
        val linkTextView = binding.irRegistrarse
        linkTextView.setLinkTextColor(Color.BLUE)
        linkTextView.setOnClickListener {
            findNavController().navigate(com.example.actividad3a.R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun getUserByEmail(email: String, user: UserRequest) {
        val call = ApiRest.service.getUserByEmail(email)
        call.enqueue(object : Callback<UsersResponse.UsersResponseItem> {
            override fun onResponse(
                call: Call<UsersResponse.UsersResponseItem>,
                response: Response<UsersResponse.UsersResponseItem>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    dataUser = body
                    //adapterJuegos?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                    val userDB = dataUser
                    if (userDB?.contrasena != binding.textFieldContrasena.editText?.text.toString()) {
                        binding.textFieldContrasena.error = "Contraseña incorrecta"
                    } else {
                        Preferences.setUserId(userDB.idUsuario)
                        Log.d("LoginFragment", userDB.idUsuario.toString())

                        //Preferences.getUser()?.let { Log.i("MainActivity" , it)
                        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(user)
                        findNavController().navigate(action)
                    }
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                    binding.textFieldEmail.error = "Este email no existe en la base de datos"
                }
            }

            override fun onFailure(call: Call<UsersResponse.UsersResponseItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }


}