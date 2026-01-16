package com.example.iglesiahosanna.subbotones.Botonconectate

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityBotonConectateBinding
import com.google.android.material.snackbar.Snackbar

class BotonConectate : AppCompatActivity() {

    private lateinit var binding: ActivityBotonConectateBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBotonConectateBinding.inflate(layoutInflater)
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

        // 3. Configurar y cargar la URL en el WebView
        val imagenlogo = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Logo%20azul%20.png"
        binding.logo2.load(imagenlogo){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
        val imagenyoutube= "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/youtube.jpeg"
        binding.youtubeIcon.load(imagenyoutube){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
        val imagenfacebook= "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/facebook.jpeg"
        binding.facebookIcon.load(imagenfacebook){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
        val imageninstagram= "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/instagram.jpeg"
        binding.instagramIcon.load(imageninstagram){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }
        val imagenspotify= "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/spotify.jpeg"
        binding.spotifyIcon.load(imagenspotify){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }

        binding.imageViewflechayoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@HosannaMinistry"))
            startActivity(intent)
        }

        binding.imageViewflechafacebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hosannaministrymadrid"))
            startActivity(intent)
        }

        binding.imageViewflechafacebook2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hosannaministrymadrid"))
            startActivity(intent)
        }
        binding.imageViewflechafacebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=61556188712412"))
            startActivity(intent)
        }
        binding.imageViewflechaspotify.setOnClickListener {
            Snackbar.make(binding.root, "Próximamente", Snackbar.LENGTH_SHORT).show()
        }
        binding.imageViewflechainstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hosannaministry_global/"))
            startActivity(intent)
        }
        binding.imageViewflechainstagram2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hosannaministrymadrid/"))
            startActivity(intent)
        }

    }

    // 4. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}