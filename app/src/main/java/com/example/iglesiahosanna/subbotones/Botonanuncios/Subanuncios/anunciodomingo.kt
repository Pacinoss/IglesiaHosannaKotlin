package com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.imageLoader
import coil.load
import com.example.iglesiahosanna.databinding.ActivityAnunciodomingoBinding

class anunciodomingo : AppCompatActivity() {

    private lateinit var binding: ActivityAnunciodomingoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnunciodomingoBinding.inflate(layoutInflater)
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
        //cargar imagen de portada
        val imageView = binding.imageanunciodomingo
        val imagenAnuncioDomingoUrl= "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/portadaanunciodomingo.jpg"

        imageView.load(imagenAnuncioDomingoUrl){
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
        }



    }

    // 3. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
