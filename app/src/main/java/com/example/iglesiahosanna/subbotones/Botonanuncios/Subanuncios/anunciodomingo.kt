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
import com.example.iglesiahosanna.databinding.ActivityAnunciodomingoBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class anunciodomingo : AppCompatActivity() {

    private lateinit var binding: ActivityAnunciodomingoBinding
    private val db = Firebase.firestore

    // Indicadores para controlar la carga de contenido
    private var isTextLoaded = false
    private var isImageLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnunciodomingoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Mostrar el ProgressBar al iniciar
        binding.progressBar.visibility = View.VISIBLE

        // Bloquear giro de pantalla y modo noche
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Configurar la Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Iniciar la carga de datos e imagen
        cargarImagen()
        binding.textView7.text = ""
        cargarDatosDomingo()
    }

    private fun cargarImagen() {
        val imageView = binding.imageanunciodomingo
        val imagenAnuncioDomingoUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/portadaanunciodomingo.jpg"

        imageView.load(imagenAnuncioDomingoUrl) {
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder) // Usar un placeholder de tu proyecto
            listener(
                onSuccess = { _, _ ->
                    isImageLoaded = true
                    checkIfLoadingIsComplete()
                },
                onError = { _, _ ->
                    isImageLoaded = true
                    checkIfLoadingIsComplete()
                }
            )
        }
    }

    private fun cargarDatosDomingo() {
        db.collection("Anuncios_Fijos").document("Domingo")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val textoDomingo = document.getString("Description")
                    val textDomingoTitulo = document.getString("Titulo")
                    binding.textView8.text = textoDomingo
                    binding.textView7.text = textDomingoTitulo
                } else {
                    Log.d("Firebase", "No se encontró el documento")
                    binding.textView8.text = "Contenido no disponible en este momento."
                }
                isTextLoaded = true
                checkIfLoadingIsComplete()
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Error al obtener el documento: ", exception)
                binding.textView8.text = "Error al cargar el contenido."
                isTextLoaded = true
                checkIfLoadingIsComplete()
            }
    }

    private fun checkIfLoadingIsComplete() {
        // Ocultar el ProgressBar solo cuando ambos contenidos han terminado de cargar
        if (isTextLoaded && isImageLoaded) {
            binding.progressBar.visibility = View.GONE
        }
    }

    // Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}