package com.example.iglesiahosanna.subbotones.Formularios

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.iglesiahosanna.UI.MainActivity
import com.example.iglesiahosanna.databinding.ActivityCongregacionesBinding
import com.example.iglesiahosanna.subbotones.Biblia.Biblia
import com.example.iglesiahosanna.subbotones.Botonanuncios.BotonAnuncios
import com.example.iglesiahosanna.subbotones.Botonconectate.BotonConectate
import com.example.iglesiahosanna.subbotones.Botondonar.BotonDonar

/**
 * Activity que muestra la información de las diferentes congregaciones.
 *
 * Esta pantalla presenta una lista de las congregaciones de la iglesia, incluyendo sus direcciones.
 * Permite al usuario abrir la dirección en Google Maps o contactar por WhatsApp.
 */
class Congregaciones : AppCompatActivity() {

    private lateinit var binding: ActivityCongregacionesBinding

    /**
     * Se llama cuando la actividad es creada por primera vez.
     *
     * Aquí es donde se realiza la mayor parte de la inicialización:
     * - Se infla el layout y se establece como el contenido de la actividad.
     * - Se configura la barra de herramientas (Toolbar) con un botón de retroceso.
     * - Se bloquea la orientación de la pantalla a vertical.
     * - Se deshabilita el modo noche.
     * - Se configuran los listeners de clic para las direcciones y el contacto de WhatsApp,
     *   dándoles un estilo de enlace (azul y subrayado).
     *
     * @param savedInstanceState Si la actividad se reinicia después de haber sido cerrada,
     * este Bundle contiene los datos que suministró más recientemente en onSaveInstanceState(Bundle).
     * Nota: De lo contrario, es nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongregacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)


        // Bloquea el giro de la pantalla a modo vertical.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Deshabilita el modo noche para mantener una apariencia consistente.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Configura la Toolbar como la ActionBar de la actividad.
        setSupportActionBar(binding.toolbar)

        // Habilita el botón de regreso (flecha) en la ActionBar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configura el TextView para la dirección de Carabanchel.
        // Al hacer clic, abre Google Maps con la dirección.
        binding.textView49.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textView49.paintFlags = binding.textView49.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textView49.setOnClickListener {
            val direccion = "C/ Eduardo Morales 1, Madrid."
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(direccion))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

        // Configura el TextView para la dirección de Fuenlabrada.
        // Al hacer clic, abre Google Maps con la dirección.
        binding.textView52.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textView52.paintFlags = binding.textView52.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textView52.setOnClickListener {
            val direccion = "C/ San Joaquín 8, Fuenlabrada."
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(direccion))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // Configura el TextView para la dirección de Navalcarnero.
        // Al hacer clic, abre Google Maps con la dirección.
        binding.textView53.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textView53.paintFlags = binding.textView53.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textView53.setOnClickListener {
            val direccion = "Plazuela del mercado 7, Navalcarnero."
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(direccion))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // Configura el TextView para la dirección de El Casar de Escalona.
        // Al hacer clic, abre Google Maps con la dirección.
        binding.textView54.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textView54.paintFlags = binding.textView54.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textView54.setOnClickListener {
            val direccion = "Paseo de los rosales 35, El Casar de Escalona, Toledo."
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(direccion))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // Configura el TextView para el contacto por WhatsApp.
        // Al hacer clic, abre WhatsApp para enviar un mensaje.
        binding.textView51.setTextColor(getColor(android.R.color.holo_blue_dark))
        binding.textView51.paintFlags = binding.textView51.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textView51.setOnClickListener {
            val numero = "+34695476650"
            val uri = Uri.parse("https://wa.me/$numero")
            val intent = Intent(Intent.ACTION_VIEW, uri)
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            R.id.nav_announcements -> {
                val intent = Intent(this, BotonAnuncios::class.java)
                startActivity(intent)
            }

            R.id.nav_live -> {
                val intent = Intent(this, BotonConectate::class.java)
                startActivity(intent)
            }

            R.id.nav_donate -> {
                val intent = Intent(this, BotonDonar::class.java)
                startActivity(intent)
            }

            R.id.nav_bible -> {
                val intent = Intent(this, Biblia::class)
                startActivity(intent)
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
