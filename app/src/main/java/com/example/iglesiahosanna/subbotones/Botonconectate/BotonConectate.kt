package com.example.iglesiahosanna.subbotones.Botonconectate

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityBotonConectateBinding
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.atomic.AtomicInteger

class BotonConectate : AppCompatActivity() {

    private lateinit var binding: ActivityBotonConectateBinding
    private val imagesToLoad = 5
    private val imagesLoadedCount = AtomicInteger(0)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBotonConectateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Inicialmente ocultamos el contenido y mostramos el progress bar
        binding.scrollContenido.visibility = View.GONE
        binding.progressBarCarga.visibility = View.VISIBLE

        cargarImagenes()
        configurarClicks()
    }

    private fun cargarImagenes() {
        val urls = listOf(
            "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Iconos%20Hosanna%2FLogo%20azul%20.png?alt=media&token=64ebdbc5-b85c-4bc3-88d0-3530bf8bd35a",
            "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Iconos%20Hosanna%2Fyoutube.jpeg?alt=media&token=6c15450f-9294-4e15-bcdc-3a2e38544493",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/facebook.jpeg",
            "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Iconos%20Hosanna%2Finstagram.jpeg?alt=media&token=a56bc274-bb7f-4bed-b4d1-5ab2c06c114a",
            "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Iconos%20Hosanna%2Fspotify.jpeg?alt=media&token=37917044-3da7-4c4d-a7c8-575b85fc42f2"
        )

        val imageViews = listOf(
            binding.logo2,
            binding.youtubeIcon,
            binding.facebookIcon,
            binding.instagramIcon,
            binding.spotifyIcon
        )

        for (i in urls.indices) {
            val request = ImageRequest.Builder(this)
                .data(urls[i])
                .target(imageViews[i])
                .listener(
                    onSuccess = { _, _ -> verificarCargaCompleta() },
                    onError = { _, _ -> verificarCargaCompleta() } // También contamos errores para no bloquear la app
                )
                .build()
            imageLoader.enqueue(request)
        }
    }

    private fun verificarCargaCompleta() {
        if (imagesLoadedCount.incrementAndGet() >= imagesToLoad) {
            runOnUiThread {
                binding.progressBarCarga.visibility = View.GONE
                binding.scrollContenido.visibility = View.VISIBLE
            }
        }
    }

    private fun configurarClicks() {
        binding.imageViewflechayoutube.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@HosannaMinistry")))
        }

        binding.imageViewflechafacebook.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=61556188712412")))
        }

        binding.imageViewflechafacebook2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hosannaministrymadrid")))
        }

        binding.imageViewflechaspotify.setOnClickListener {
            Snackbar.make(binding.root, "Próximamente", Snackbar.LENGTH_SHORT).show()
        }

        binding.imageViewflechainstagram.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hosannaministry_global/")))
        }

        binding.imageViewflechainstagram2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hosannaministrymadrid/")))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
