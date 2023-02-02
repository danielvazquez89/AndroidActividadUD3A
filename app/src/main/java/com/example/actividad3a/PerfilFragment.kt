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
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.actividad3a.databinding.FragmentPerfilBinding
import java.io.File

class PerfilFragment : Fragment() {
    private var _binding: FragmentPerfilBinding? = null
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivImage = binding.miImagenUsuario
        /*findViewById<Button>(R.id.btCam).setOnClickListener {
            startCamera()
        }
        findViewById<Button>(R.id.btGal).setOnClickListener {
            startGallery()
        }
        */
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Upload image")
        builder.setMessage("Choose uploading image way")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Camera") { dialog, which ->
            Toast.makeText(
                requireContext(),
                "Camera", Toast.LENGTH_SHORT
            ).show()
            // Check permissions camera
            option = getString(R.string.cameraOption)
            checkPermissions()
            startCamera()
        }

        builder.setNegativeButton("Gallery") { dialog, which ->
            Toast.makeText(
                requireContext(),
                "Gallery", Toast.LENGTH_SHORT
            ).show()
            // Check permissions gallery
            option = "Gallery"
            checkPermissions()
            startGallery()
        }

        builder.setNeutralButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                requireContext(),
                "Canceled", Toast.LENGTH_SHORT
            ).show()
        }

        binding.miEditFoto.setOnClickListener {
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
            requireContext(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun permissionCameraGranted(): Boolean = REQUIRE_CAMERA.all {
        ActivityCompat.checkSelfPermission(
            requireContext(),
            it
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
            requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }
}