package com.example.actividad3a

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.GenerosResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentUploadBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvGeneros: RecyclerView
    val TAG = "UploadFragment"
    var data: ArrayList<GenerosResponse.GenerosResponseItem> = ArrayList()
    private var adapter: UploadAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
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
        /*
        var genre_content_list = listOf(genre_content("https://cdn-icons-png.flaticon.com/512/2790/2790402.png", "Aventura"), genre_content("https://cdn-icons-png.flaticon.com/512/8027/8027925.png", "Acción"), genre_content("https://cdn-icons-png.flaticon.com/512/5846/5846307.png", "Arcade")
            , genre_content("https://cdn1.iconfinder.com/data/icons/game-design-butterscotch-vol-2/256/Sports_Game-1024.png", "Deportes"), genre_content("https://cdn.imgbin.com/2/13/18/imgbin-chess-computer-icons-board-game-strategy-video-game-chess-H0QHtkEXBGcqywU54PWv3d2xg.jpg", "Estrategia"))
        val mAdapter = UploadAdapter(genre_content_list) {
            //val directions = HomeFragment.actionBuscarFragmentToCancionesFragment(it)
            //findNavController().navigate(directions)
        }
         */
        rvGeneros = binding.genreUploadRecyclerView
        //loader = findViewById<View>(R.id.loader)
        val mLayoutManager = GridLayoutManager(context, 2)
        rvGeneros.layoutManager = mLayoutManager

        getGeneros()

        //Creamos el adapter y lo vinculamos con el recycler
        adapter = UploadAdapter(data) {
            val directions = UploadFragmentDirections.actionUploadFragmentToUploadDetailsFragment(it.tipoGenero)
            findNavController().navigate(directions)
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        }
        rvGeneros.adapter = adapter
        val mainRecyclerView: RecyclerView = binding.genreUploadRecyclerView
        mainRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //mainRecyclerView.adapter = mAdapter
    }

    private fun getGeneros() {
        val call = ApiRest.service.getGeneros()
        call.enqueue(object : Callback<GenerosResponse> {
            override fun onResponse(
                call: Call<GenerosResponse>,
                response: Response<GenerosResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body)
                    adapter?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<GenerosResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}