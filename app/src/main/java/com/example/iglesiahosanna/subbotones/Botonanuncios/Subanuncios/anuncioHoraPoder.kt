package com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityHoraPoderBinding

/**
 * Activity para mostrar un anuncio específico llamado "Hora de Poder".
 *
 * Esta pantalla muestra una imagen de un anuncio cargada desde una URL y un enlace de texto
 * que redirige a una publicación de Facebook.
 */
class anuncioHoraPoder : AppCompatActivity() {

    private lateinit var binding: ActivityHoraPoderBinding

    /**
     * Se llama cuando la actividad se crea por primera vez.
     *
     * Inicializa la vista, configura la barra de herramientas, carga la imagen del anuncio
     * y establece el comportamiento del enlace de texto.
     *
     * @param savedInstanceState Si la actividad se está reiniciando después de haber sido
     *                           previamente cerrada, este Bundle contiene los datos que
     *                           suministró más recientemente en onSaveInstanceState(Bundle).
     *                           De lo contrario, es nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoraPoderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)


        // Bloquea la orientación de la pantalla a vertical.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Deshabilita el modo nocturno para mantener una apariencia consistente.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // 1. Configura la Toolbar como la ActionBar de la actividad.
        setSupportActionBar(binding.toolbar)

        // 2. Habilita el botón de regreso (flecha) en la ActionBar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Carga la imagen del anuncio desde una URL usando la librería Coil.
        val imageUrl = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio3HORAPODER.jpg"
        binding.imageanuncionsabado.load(imageUrl){
            crossfade(true) // Habilita una transición suave al cargar la imagen.
            placeholder(R.drawable.anuncio_placeholder) // Muestra una imagen de placeholder mientras se carga la real.
        }

        // Configura el TextView como un enlace clickeable.
        // Le da un color azul y lo subraya para que parezca un enlace web.
        binding.textviewenlace.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textviewenlace.paintFlags = binding.textviewenlace.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        // Al hacer clic, abre la URL de Facebook en un navegador.
        binding.textviewenlace.setOnClickListener {
            val url = "https://www.facebook.com/share/1Ga5rkB57N/?mibextid=wwXIfr"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

    }

    /**
     * Maneja el evento de clic en el botón de regreso de la ActionBar.
     *
     * Este método se invoca cuando el usuario presiona el botón de retroceso en la barra de herramientas.
     * Finaliza la actividad actual y regresa a la pantalla anterior en la pila de actividades.
     *
     * @return `true` para indicar que el evento ha sido manejado.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
