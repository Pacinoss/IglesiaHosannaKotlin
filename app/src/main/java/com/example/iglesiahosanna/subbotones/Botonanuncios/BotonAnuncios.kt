package com.example.iglesiahosanna.subbotones.Botonanuncios

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityBotonAnunciosBinding
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.*

class BotonAnuncios : AppCompatActivity() {

    private lateinit var binding: ActivityBotonAnunciosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityBotonAnunciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupImages()
        setupClickListeners()
    }

    private fun setupImages() {
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_1(Domingo).jpg", binding.imageButtondomingo, binding.pbDomingo)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_2(Miercoles).jpg", binding.imageButtonmiercoles, binding.pbMiercoles)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_3(viernes).jpg", binding.imageButtonviernes, binding.pbViernes)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/tardejoven.jpg", binding.imageButtonsabado, binding.pbSabado)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio3HORAPODER.jpg", binding.imageButtonhorapoder, binding.pbHoraPoder)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio2ISRAEL.jpg", binding.imageButtonisrael, binding.pbIsrael)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/main/Anuncio1AYUNO.jpg", binding.imageButtonayuno, binding.pbAyuno)
        loadImage("https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_4Extra.jpg", binding.imageButtonExtraordinario, binding.pbExtraordinario)
    }

    private fun loadImage(url: String, imageView: View, progressBar: ProgressBar) {
        if (url.isNotBlank()) {
            progressBar.visibility = View.VISIBLE
            (imageView as? android.widget.ImageView)?.load(url) {
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
                listener(
                    onSuccess = { _, _ -> progressBar.visibility = View.GONE },
                    onError = { _, _ -> progressBar.visibility = View.GONE }
                )
            }
        } else {
            imageView.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    private fun setupClickListeners() {
        binding.imageButtondomingo.setOnClickListener { startActivity(Intent(this, anunciodomingo::class.java)) }
        binding.imageButtonmiercoles.setOnClickListener { startActivity(Intent(this, anunciomiercoles::class.java)) }
        binding.imageButtonviernes.setOnClickListener { startActivity(Intent(this, anuncioviernes::class.java)) }
        binding.imageButtonsabado.setOnClickListener { startActivity(Intent(this, anunciosabado::class.java)) }
        binding.imageButtonhorapoder.setOnClickListener { startActivity(Intent(this, anuncioHoraPoder::class.java)) }
        binding.imageButtonisrael.setOnClickListener { startActivity(Intent(this, anuncioextraordinario::class.java)) }
        binding.imageButtonayuno.setOnClickListener { startActivity(Intent(this, anuncioextraordinario::class.java)) }
        binding.imageButtonExtraordinario.setOnClickListener { startActivity(Intent(this, anuncioextraordinario::class.java)) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
