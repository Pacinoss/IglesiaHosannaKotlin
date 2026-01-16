package com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityAnunciosabadoBinding

class anunciosabado : AppCompatActivity() {

    private lateinit var binding: ActivityAnunciosabadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnunciosabadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val imageUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/tardejoven.jpg"
        binding.imageanuncionviernes.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
    }

    // 3. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}