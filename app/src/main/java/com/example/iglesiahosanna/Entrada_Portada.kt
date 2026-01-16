package com.example.iglesiahosanna

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.iglesiahosanna.UI.MainActivity
import com.example.iglesiahosanna.databinding.ActivityEntradaPortadaBinding
import com.google.android.material.snackbar.Snackbar

class Entrada_Portada : AppCompatActivity() {
    private lateinit var binding: ActivityEntradaPortadaBinding
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntradaPortadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Configurar botones
        binding.botonMadrid.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.botonMurcia.setOnClickListener {
            Snackbar.make(binding.root, "Próximamente", Snackbar.LENGTH_SHORT).show()
        }
        binding.botonEcuador.setOnClickListener {
            Snackbar.make(binding.root, "Próximamente", Snackbar.LENGTH_SHORT).show()
        }
        
        // --- Configuración del Carrusel ---
        val imageUrls = listOf(
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/main/Anuncio1AYUNO.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio3HORAPODER.jpg",
            "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Anuncio2ISRAEL.jpg"
        )

        binding.activitiesCarouselPortada.apply {
            adapter = CarouselAdapter(imageUrls)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            (getChildAt(0) as RecyclerView).setHasFixedSize(true)

            val compositeTransformer = CompositePageTransformer()
            compositeTransformer.addTransformer(MarginPageTransformer(40))
            compositeTransformer.addTransformer(ZoomOutPageTransformer())
            setPageTransformer(compositeTransformer)
        }

        setupAutoSlider(binding.activitiesCarouselPortada, imageUrls.size)
    }

    private fun setupAutoSlider(viewPager: ViewPager2, imageCount: Int) {
        sliderHandler = Handler(Looper.getMainLooper())
        sliderRunnable = Runnable {
            var currentItem = viewPager.currentItem
            currentItem++
            if (currentItem >= imageCount) {
                currentItem = 0
            }
            viewPager.setCurrentItem(currentItem, true)
            sliderHandler.postDelayed(sliderRunnable, 5000)
        }
    }

    override fun onResume() {
        super.onResume()
        if (::sliderHandler.isInitialized) {
            sliderHandler.postDelayed(sliderRunnable, 5000)
        }
    }

    override fun onPause() {
        super.onPause()
        if (::sliderHandler.isInitialized) {
            sliderHandler.removeCallbacks(sliderRunnable)
        }
    }
}