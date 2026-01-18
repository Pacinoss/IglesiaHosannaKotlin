package com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityAnunciomiercolesBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class anunciomiercoles : AppCompatActivity() {

    private lateinit var binding: ActivityAnunciomiercolesBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityAnunciomiercolesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val imagebuttonmiercolesUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_2(Miercoles).jpg"
        binding.imageanuncionmiercoles.load(imagebuttonmiercolesUrl) {
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
        
        cargarDatosMiercoles()
    }

    private fun cargarDatosMiercoles() {
        // Mostramos el progress bar
        binding.progressBarTextView13.visibility = View.VISIBLE
        
        db.collection("Anuncios_Fijos").document("Miercoles")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val textoMiercoles = document.getString("Description")
                    val textMiercolesTitulo = document.getString("Titulo")
                    binding.textView13.text = textoMiercoles
                    binding.textView6.text = textMiercolesTitulo
                }
                // Quitamos el progress bar
                binding.progressBarTextView13.visibility = View.GONE
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Error al obtener el documento", exception)
                binding.progressBarTextView13.visibility = View.GONE
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
