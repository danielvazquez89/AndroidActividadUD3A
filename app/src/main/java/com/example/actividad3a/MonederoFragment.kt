package com.example.actividad3a

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.Preferences
import com.example.actividad3a.data.models.UsersResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentBuzonBinding
import com.example.actividad3a.databinding.FragmentMonederoBinding
import com.example.actividad3a.databinding.FragmentVentasBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonederoFragment : Fragment() {

    private var _binding: FragmentMonederoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMonederoBinding.inflate(inflater, container, false)
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
                    Log.i(ContentValues.TAG, body.toString())
                    binding.miTextoMonedero.text = body.saldoMonedero.toString()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(ContentValues.TAG, it) }
                }
            }

            override fun onFailure(call: Call<UsersResponse.UsersResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, t.message.toString())
            }
        })
    }

}