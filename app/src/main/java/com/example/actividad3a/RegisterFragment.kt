package com.example.actividad3a

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.actividad3a.databinding.FragmentRegisterBinding
import com.example.navigationcomponent.Validaciones
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment() {
    //val args: RegisterFragmentArgs by navArgs()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        setupActivityLink()
        binding.textFechaNacimiento.transformIntoDatePicker(requireContext(), "MM/dd/yyyy")
        binding.textFechaNacimiento.transformIntoDatePicker(requireContext(), "MM/dd/yyyy", Date())
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isVisible = false
        binding.btRegistrarse.setOnClickListener {
            val objetoValidaciones = Validaciones()
            val emailValido = objetoValidaciones.esEmailValido(binding.textFieldEmail)
            val nombreValido = objetoValidaciones.esNombreValido(binding.textFieldNombre)
            val apellidosValidos = objetoValidaciones.esNombreValido(binding.textFieldApellidos)
            val validoContrasena = objetoValidaciones.esContrasenaValida(binding.textFieldContrasena)
            var fechaVacia = false
            if (binding.textFieldFechaNacimiento.editText?.text?.isEmpty() == true) {
                fechaVacia = true
                binding.textFieldFechaNacimiento.error = "Indique su fecha de nacimiento por favor"
            } else {
                binding.textFieldFechaNacimiento.error = ""
            }
            if (emailValido && nombreValido && apellidosValidos && validoContrasena && !fechaVacia) {
                //Registrarse
                val nombre = binding.textFieldNombre.editText?.text.toString()
                val apellidos = binding.textFieldApellidos.editText?.text.toString()
                val email = binding.textFieldEmail.editText?.text.toString()
                //val contrasena = binding.textFieldContrasena.editText?.text.toString()
                val fechaNacimiento = binding.textFieldFechaNacimiento.editText?.text.toString()
                val user = User(nombre,email,apellidos,fechaNacimiento)
                val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment(user)
                findNavController().navigate(action)
            }
        }
    }

    fun setupActivityLink() {
        val linkTextView = binding.iniciarSesion
        linkTextView.setLinkTextColor(Color.BLUE)
        linkTextView.setOnClickListener {
            findNavController().navigate( com.example.actividad3a.R.id.action_registerFragment_to_loginFragment)
        }
    }
    data class User(val name: String, val email: String, val apellidos: String, val fecha: String) : Serializable

    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

}