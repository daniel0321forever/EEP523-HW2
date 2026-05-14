package com.example.hw2

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import java.io.OutputStream

class DrawFragment : Fragment() {

    private lateinit var drawingView: DrawingView
    private var originalBitmap: Bitmap? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            imageUri?.let { uri ->
                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                originalBitmap = bitmap
                drawingView.setBackgroundImage(bitmap)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_draw, container, false)

        drawingView = view.findViewById(R.id.drawing_view)
        val btnPickImage = view.findViewById<Button>(R.id.btn_pick_image)
        val btnReset = view.findViewById<Button>(R.id.btn_reset)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        btnPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        btnReset.setOnClickListener {
            drawingView.reset()
        }

        btnSave.setOnClickListener {
            saveImageToGallery()
        }

        view.findViewById<View>(R.id.color_black).setOnClickListener { drawingView.setColor(0xFF000000.toInt()) }
        view.findViewById<View>(R.id.color_red).setOnClickListener { drawingView.setColor(0xFFFF0000.toInt()) }
        view.findViewById<View>(R.id.color_green).setOnClickListener { drawingView.setColor(0xFF00FF00.toInt()) }
        view.findViewById<View>(R.id.color_blue).setOnClickListener { drawingView.setColor(0xFF0000FF.toInt()) }

        return view
    }

    private fun saveImageToGallery() {
        val bitmap = drawingView.getBitmap() ?: return
        val filename = "Edited_${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireContext().contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/HW2")
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DCIM).toString()
            val image = java.io.File(imagesDir, filename)
            fos = java.io.FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Image Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }
}
