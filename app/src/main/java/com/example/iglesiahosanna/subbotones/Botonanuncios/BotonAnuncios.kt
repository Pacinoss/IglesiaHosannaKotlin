package com.example.iglesiahosanna.subbotones.Botonanuncios

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityBotonAnunciosBinding
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciodomingo
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciomiercoles
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciosabado
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioviernes
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioextraordinario
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioHoraPoder

class BotonAnuncios : AppCompatActivity() {

    private lateinit var binding: ActivityBotonAnunciosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)


        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityBotonAnunciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        ////////////////////////////añadimos los enlaces de las imagenes////////////////////////////
        // 3. Cargar las imágenes desde la URL y gestionar la visibilidad
        val imagebuttondomingoUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_1(Domingo).jpg"
        if (imagebuttondomingoUrl.isNotBlank()) {
            binding.imageButtondomingo.visibility = View.VISIBLE
            binding.imageButtondomingo.load(imagebuttondomingoUrl) {
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtondomingo.visibility = View.GONE
        }

        val imagebuttonmiercolesUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_2(Miercoles).jpg"
        if (imagebuttonmiercolesUrl.isNotBlank()) {
            binding.imageButtonmiercoles.visibility = View.VISIBLE
            binding.imageButtonmiercoles.load(imagebuttonmiercolesUrl) {
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonmiercoles.visibility = View.GONE
        }

        val imagebuttonviernesUrl ="https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_3(viernes).jpg"
        if (imagebuttonviernesUrl.isNotBlank()) {
            binding.imageButtonviernes.visibility = View.VISIBLE
            binding.imageButtonviernes.load(imagebuttonviernesUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonviernes.visibility = View.GONE
        }

        val imagebuttonsabadoUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/tardejoven.jpg"
        if (imagebuttonsabadoUrl.isNotBlank()) {
            binding.imageButtonsabado.visibility = View.VISIBLE
            binding.imageButtonsabado.load(imagebuttonsabadoUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonsabado.visibility = View.GONE
        }

        val imagebuttonhorapoderUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio3HORAPODER.jpg"
        if (imagebuttonhorapoderUrl.isNotBlank()) {
            binding.imageButtonhorapoder.visibility = View.VISIBLE
            binding.imageButtonhorapoder.load(imagebuttonhorapoderUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonhorapoder.visibility = View.GONE
        }

        val imagebuttonisraelUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio2ISRAEL.jpg"
        if (imagebuttonisraelUrl.isNotBlank()) {
            binding.imageButtonisrael.visibility = View.VISIBLE
            binding.imageButtonisrael.load(imagebuttonisraelUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonisrael.visibility = View.GONE
        }

        val imagebuttonayunoUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/main/Anuncio1AYUNO.jpg"
        if (imagebuttonayunoUrl.isNotBlank()) {
            binding.imageButtonayuno.visibility = View.VISIBLE
            binding.imageButtonayuno.load(imagebuttonayunoUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonayuno.visibility = View.GONE
        }

        val imagebuttonExtraordinarioUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_4Extra.jpg"
        if (imagebuttonExtraordinarioUrl.isNotBlank()) {
            binding.imageButtonExtraordinario.visibility = View.GONE
            binding.imageButtonExtraordinario.load(imagebuttonExtraordinarioUrl){
                crossfade(true)
                placeholder(R.drawable.anuncio_placeholder)
            }
        } else {
            binding.imageButtonExtraordinario.visibility = View.GONE
        }

        ////////////////////////añadimos las aciones de las imagenws de los botones/////////////////////////////////
        // 4. Configurar los Click Listeners para los ImageButtons
        binding.imageButtondomingo.setOnClickListener {
            val intent = Intent(this, anunciodomingo::class.java)
            startActivity(intent)
        }
        binding.imageButtonmiercoles.setOnClickListener {
            val intent = Intent(this, anunciomiercoles::class.java)
            startActivity(intent)
        }
        binding.imageButtonviernes.setOnClickListener {
            val intent = Intent(this, anuncioviernes::class.java)
            startActivity(intent)
        }
        binding.imageButtonsabado.setOnClickListener {
            val intent = Intent(this, anunciosabado::class.java)
            startActivity(intent)
        }
        binding.imageButtonhorapoder.setOnClickListener {
            val intent = Intent(this, anuncioHoraPoder::class.java)
            startActivity(intent)
        }
        binding.imageButtonisrael.setOnClickListener {
            val intent = Intent(this, anuncioextraordinario::class.java)
            startActivity(intent)
        }
        binding.imageButtonayuno.setOnClickListener {
            val intent = Intent(this, anuncioextraordinario::class.java)
            startActivity(intent)
        }
        binding.imageButtonExtraordinario.setOnClickListener {
            val intent = Intent(this, anuncioextraordinario::class.java)
            startActivity(intent)
        }
    }

    // 5. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}