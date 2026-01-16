package com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios

import android.content.pm.ActivityInfo
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityAnuncioextraordinarioBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class anuncioextraordinario : AppCompatActivity() {

    private lateinit var binding: ActivityAnuncioextraordinarioBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnuncioextraordinarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /*// 4. Configurar el click listener para el TextView de suscripción
        binding.textViewsuscripcion.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textViewsuscripcion.paintFlags = binding.textViewsuscripcion.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textViewsuscripcion.setOnClickListener {
            Snackbar.make(binding.root, "Próximamente", Snackbar.LENGTH_SHORT).show()
        }

         */

        val imageUrl = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Anuncios%20Extraordinarios%2FAnuncio2ISRAEL.jpg?alt=media&token=913e6cbb-4dbd-4b24-978d-4db7c7ee5c36"
        binding.imageanuncionisrael.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
            listener(
                onStart = { binding.progressBarIsrael.visibility = View.VISIBLE },
                onSuccess = { _, _ -> binding.progressBarIsrael.visibility = View.GONE },
                onError = { _, _ -> binding.progressBarIsrael.visibility = View.GONE }
            )
        }

        val imageUrl2 = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Anuncios%20Extraordinarios%2FAnuncio1AYUNO.jpg?alt=media&token=fe4d9bc2-cebf-4008-a240-6f77524f16ef"
        binding.imageanuncionayuno.load(imageUrl2) {
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
            listener(
                onStart = { binding.progressBarAyuno.visibility = View.VISIBLE },
                onSuccess = { _, _ -> binding.progressBarAyuno.visibility = View.GONE },
                onError = { _, _ -> binding.progressBarAyuno.visibility = View.GONE }
            )
        }

        // Cargar datos de Firebase
        cargarDatosExtraordinario1()
        cargarDatosExtraordinario2()
    }






    private fun cargarDatosExtraordinario1() {
        db.collection("Textos_AnunciosExtraordinarios").document("Anuncio_Extra1")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val textoDescription = document.getString("Description") // Asume que el campo se llama "extra....."
                    val textTitulo = document.getString("Titulo")
                    binding.textViewBasedatos.text = textoDescription
                    binding.textViewbasetitulo.text = textTitulo
                } else {
                    Log.d("Firebase", "No se encontró el documento")
                    binding.textViewBasedatos.text = "Contenido no disponible en este momento."
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Error al obtener el documento: ", exception)
                binding.textViewBasedatos.text = "Error al cargar el contenido."
            }
    }

    private fun cargarDatosExtraordinario2() {
        db.collection("Textos_AnunciosExtraordinarios").document("Anuncio_Extra2")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val texto = document.getString("Description") // Asume que el campo se llama "extra....."
                    binding.textViewbasededatos2.text = texto
                } else {
                    Log.d("Firebase", "No se encontró el documento")
                    binding.textViewbasededatos2.text = "Contenido no disponible en este momento."
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Error al obtener el documento: ", exception)
                binding.textViewbasededatos2.text = "Error al cargar el contenido."
            }
    }



    // 3. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}