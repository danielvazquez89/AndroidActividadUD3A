package com.example.actividad3a

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad3a.data.models.MensajesChatResponse
import com.example.actividad3a.data.remotes.ApiRest
import com.example.actividad3a.databinding.FragmentMensajesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MensajesFragment : Fragment() {
    val args: MensajesFragmentArgs by navArgs()
    private var _binding: FragmentMensajesBinding? = null
    private val binding get() = _binding!!
    private var adapterMensajes: MensajesAdapter? = null
    var dataMensajes: ArrayList<MensajesChatResponse.MensajesChatResponseItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMensajesBinding.inflate(inflater, container, false)
        val view = binding.root
        setActivityTitle("Mensajes")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = true

       // var genre_content_list = listOf(message_content(" 13:40","donde podriamos quedar??"))

        getMensajesChat(args.idChat)
        adapterMensajes = MensajesAdapter(dataMensajes)

        val mainRecyclerView: RecyclerView = binding.mensajesRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(context, 1)

        mainRecyclerView.adapter = adapterMensajes
    }
    private fun getMensajesChat(idChat: Int) {
        val call = ApiRest.service.getMensajesEnChat(idChat)
        call.enqueue(object : Callback<MensajesChatResponse> {
            override fun onResponse(
                call: Call<MensajesChatResponse>,
                response: Response<MensajesChatResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(ContentValues.TAG, body.toString())
                    dataMensajes.clear()
                    dataMensajes.addAll(body)
                    adapterMensajes?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(ContentValues.TAG, it) }
                }
            }

            override fun onFailure(call: Call<MensajesChatResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, t.message.toString())
            }
        })
    }

}