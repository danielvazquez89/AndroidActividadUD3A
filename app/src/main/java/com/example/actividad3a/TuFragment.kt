package com.example.actividad3a

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.R
import com.example.actividad3a.data.models.GenerosResponse
import com.example.actividad3a.data.models.Preferences
import com.example.actividad3a.data.models.UsersResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentTuBinding
import com.example.actividad3a.setActivityTitle
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TuFragment : Fragment() {
    private var _binding: FragmentTuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Tu")
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

        var userId = 0
        Preferences.getUserId()?.let {
            Log.i("MainActivity", it)
            userId = it.toInt()
        }
        getUserbyId(userId)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = true
        binding.irAPerfilLayout.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToPerfilFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        binding.irACompras.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToComprasFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        binding.irAVentas.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToVentasFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        binding.irAMonedero.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToMonederoFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        binding.irAConfiguracion.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToConfiguracionFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        binding.irAAyuda.setOnClickListener {
            val directions =
                com.example.actividad3a.TuFragmentDirections.actionTuFragmentToAyudaFragment()
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
    }

    private fun getUserbyId(id: Int) {
        val call = ApiRest.service.getUserById(id)
        call.enqueue(object : Callback<UsersResponse.UsersResponseItem> {
            override fun onResponse(
                call: Call<UsersResponse.UsersResponseItem>,
                response: Response<UsersResponse.UsersResponseItem>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    binding.nombreUsuario.text = "${body.nombre} ${body.apellidos}"
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<UsersResponse.UsersResponseItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}