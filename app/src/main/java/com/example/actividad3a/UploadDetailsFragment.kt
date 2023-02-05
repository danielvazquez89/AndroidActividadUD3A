package com.example.actividad3a

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.actividad3a.databinding.FragmentUploadDetailsBinding
import java.io.File

class UploadDetailsFragment : Fragment() {
    private var _binding: FragmentUploadDetailsBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_CODE_PERMISSIONS = 1
    private val REQUIRE_CAMERA = arrayOf(
        Manifest.permission.CAMERA
    )
    private val REQUIRE_GALLERY = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    private lateinit var option: String
    private lateinit var ivImage: ImageView

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        ivImage.setImageURI(uri)
    }

    private var latestTmpUri: Uri? = null
    private val takeImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { ok ->
            if (ok) {
                latestTmpUri?.let { uri ->
                    ivImage.setImageURI(uri)
                }
            }
        }
    val args: UploadDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setActivityTitle("Subiendo en " + args.genero)
        _binding = FragmentUploadDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivImage = binding.miImagenJuego
        binding.miBotonUpload.setOnClickListener {
            if (binding.miPrecioJuego.text.toString() != "" && binding.miTituloJuego.text.toString() != "" && binding.miConsolaJuego.text.toString() != "" && binding.miDescripcionJuego.text.toString() != "") {
                Toast.makeText(
                    requireContext(), "Producto subido", Toast.LENGTH_SHORT
                ).show()
                val directions =
                    UploadDetailsFragmentDirections.actionUploadDetailsFragmentToUploadFragment()
                findNavController().navigate(directions)
            } else {
                Toast.makeText(
                    requireContext(), "Rellene todos los campos por favor", Toast.LENGTH_SHORT
                ).show()
            }
        }
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Subir imagen")
        builder.setMessage("Seleccione la forma en la que quiere subir su imagne")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Camara") { dialog, which ->
            Toast.makeText(
                requireContext(), "Camara", Toast.LENGTH_SHORT
            ).show()
            // Check permissions camera
            option = getString(R.string.cameraOption)
            checkPermissions()
            startCamera()
        }

        builder.setNegativeButton("Galleria") { dialog, which ->
            Toast.makeText(
                requireContext(), "Galleria", Toast.LENGTH_SHORT
            ).show()
            // Check permissions gallery
            option = "Gallery"
            checkPermissions()
            startGallery()
        }

        builder.setNeutralButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                requireContext(), "Canceled", Toast.LENGTH_SHORT
            ).show()
        }

        binding.miImagenJuego.setOnClickListener {
            builder.show()
        }
    }

    private fun checkPermissions() {
        if (option == getString(R.string.cameraOption) && !permissionCameraGranted()) {
            requestPermissions(REQUIRE_CAMERA, REQUEST_CODE_PERMISSIONS)
        } else if (option == "Gallery" && !permissionGalleryGranted()) {
            requestPermissions(REQUIRE_GALLERY, REQUEST_CODE_PERMISSIONS)
        }
    }

    fun permissionGalleryGranted(): Boolean = REQUIRE_GALLERY.all {
        ActivityCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun permissionCameraGranted(): Boolean = REQUIRE_CAMERA.all {
        ActivityCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun startCamera() {
        if (permissionCameraGranted()) {
            getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)
            }
        } else {
            //Mostrar error al usuario
        }
    }

    private fun startGallery() {
        getContent.launch("image/*")
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile =
            File.createTempFile("tmp_image_file", ".png", requireActivity().cacheDir).apply {
                createNewFile()
                deleteOnExit()
            }
        return FileProvider.getUriForFile(
            requireContext(), "${BuildConfig.APPLICATION_ID}.provider", tmpFile
        )
    }
}

fun Fragment.setActivityTitle(title: String) {
    (activity as AppCompatActivity?)?.supportActionBar?.title = title
}

