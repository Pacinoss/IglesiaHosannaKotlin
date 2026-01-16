package com.example.iglesiahosanna.UI

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import coil.imageLoader
import coil.load
import com.example.iglesiahosanna.CarouselAdapter
import com.example.iglesiahosanna.Entrada_Portada
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.ZoomOutPageTransformer
import com.example.iglesiahosanna.databinding.ActivityMainBinding
import com.example.iglesiahosanna.subbotones.Biblia.Biblia
import com.example.iglesiahosanna.subbotones.Botonanuncios.BotonAnuncios
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciodomingo
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciomiercoles
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioviernes
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioextraordinario
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anunciosabado
import com.example.iglesiahosanna.subbotones.Botonanuncios.Subanuncios.anuncioHoraPoder
import com.example.iglesiahosanna.subbotones.Botonconectate.BotonConectate
import com.example.iglesiahosanna.subbotones.Botondonar.BotonDonar
import com.example.iglesiahosanna.subbotones.Formularios.Congregaciones
import com.example.iglesiahosanna.subbotones.Formularios.Peticiones
import com.example.iglesiahosanna.subbotones.Formularios.Testimonios
import com.example.iglesiahosanna.subbotones.Formularios.gruposconexion

/**
 * Actividad principal de la aplicación.
 *
 * Muestra la pantalla de inicio con un carrusel de imágenes, una imagen de portada y varios botones
 * de navegación que dirigen a diferentes secciones de la app. También incluye una barra de
 * navegación inferior para acceder a las funcionalidades principales.
 */
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    /**
     * Contiene las URLs de las imágenes que se mostrarán en el carrusel de la pantalla principal.
     */
    companion object {
        private val IMAGE_URLS = listOf(
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_1(Domingo).jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_2(Miercoles).jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_3(viernes).jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/tardejoven.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio3HORAPODER.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio2ISRAEL.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/main/Anuncio1AYUNO.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio1_4Extra.jpg"
        )

    }


    /**
     * Se llama cuando la actividad es creada por primera vez.
     *
     * Aquí es donde se realiza la mayor parte de la inicialización:
     * - Se infla el layout y se establece como el contenido de la actividad.
     * - Se bloquea la orientación de la pantalla a vertical y se deshabilita el modo noche.
     * - Se cargan las imágenes del logo y de la portada.
     * - Se configura el carrusel de imágenes con un adaptador y transformaciones de página.
     * - Se configura el listener para la barra de navegación inferior.
     * - Se inicializan los listeners para los botones de la pantalla.
     *
     * @param savedInstanceState Si la actividad se reinicia después de haber sido cerrada,
     * este Bundle contiene los datos que suministró más recientemente en onSaveInstanceState(Bundle).
     * Nota: De lo contrario, es nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val imagenlogo = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Logo%20azul%20.png"
        binding.logo.load(imagenlogo){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }


        val imagenportada = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Portada_Madrid.jpg"
        binding.imageViewportadamadrid.load(imagenportada){
            crossfade(true)
            placeholder(R.drawable.anuncio_placeholder)
        }


        // --- Configuración del Carrusel ---
        val filteredUrls = IMAGE_URLS.filter { it.isNotBlank() }

        if (filteredUrls.isEmpty()) {
            binding.activitiesCarousel.visibility = View.GONE
        } else {
            binding.activitiesCarousel.visibility = View.VISIBLE
            binding.activitiesCarousel.apply {
                adapter = CarouselAdapter(filteredUrls)
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3

                // Optimización para mayor fluidez
                (getChildAt(0) as RecyclerView).setHasFixedSize(true)

                // Combinar múltiples transformaciones para el efecto
                val compositeTransformer = CompositePageTransformer()
                compositeTransformer.addTransformer(MarginPageTransformer(40))
                compositeTransformer.addTransformer(ZoomOutPageTransformer())
                setPageTransformer(compositeTransformer)
            }
        }

        // Configurar el listener para la barra de navegación inferior
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, Entrada_Portada::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                R.id.nav_announcements -> {
                    val intent = Intent(this, BotonAnuncios::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_live -> {
                    val intent = Intent(this, BotonConectate::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_donate -> {
                    val intent = Intent(this, BotonDonar::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_bible -> {
                    val intent = Intent(this, Biblia::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
        botonEnlace()
        botonPeticiones()
        botonCongregaciones()
        botonGruposConexion()
        botonTestimonio()
    }

    /**
     * Se llama cuando la actividad está a punto de ser destruida.
     *
     * Limpia la caché de memoria de Coil para liberar recursos de imágenes que ya no se necesitan.
     */
    override fun onDestroy() {
        super.onDestroy()
        // Limpia la caché de memoria de Coil para liberar recursos inmediatamente
        imageLoader.memoryCache?.clear()
    }

    /**
     * Configura el listener de clic para el enlace de información.
     * Abre una URL externa en el navegador.
     */
    private fun botonEnlace(){
        binding.enlaceinfo.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://mhglobal.org/minish")
            )
            startActivity(intent)
        }
    }

    /**
     * Configura el listener de clic para el botón de peticiones.
     * Inicia la actividad [Peticiones].
     */
    private fun botonPeticiones() {
        binding.buttonpeticiones.setOnClickListener {
            val intent = Intent(this, Peticiones::class.java)
            startActivity(intent)
        }
    }

    /**
     * Configura el listener de clic para el botón de congregaciones.
     * Inicia la actividad [Congregaciones].
     */
    private fun botonCongregaciones() {
        binding.buttoncongregaciones.setOnClickListener {
            val intent = Intent(this, Congregaciones::class.java)
            startActivity(intent)
        }
    }

    /**
     * Configura el listener de clic para el botón de grupos de conexión.
     * Inicia la actividad [gruposconexion].
     */
    private fun botonGruposConexion() {
        binding.buttongrupoconexion.setOnClickListener {
            val intent = Intent(this, gruposconexion::class.java)
            startActivity(intent)
        }
    }

    /**
     * Configura el listener de clic para el botón de testimonios.
     * Inicia la actividad [Testimonios].
     */
    private fun botonTestimonio() {
        binding.buttontestimonio.setOnClickListener {
            val intent = Intent(this, Testimonios::class.java)
            startActivity(intent)
        }
    }
}
