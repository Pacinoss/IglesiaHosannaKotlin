package com.example.iglesiahosanna.subbotones.Biblia

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.iglesiahosanna.databinding.ActivityBibliaBinding

class Biblia : AppCompatActivity() {

    private lateinit var binding: ActivityBibliaBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBibliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // 3. Configurar y cargar la URL en el WebView
        binding.webviewBiblia.apply {
            // Controlar la visibilidad mientras carga
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBarBiblia.visibility = View.VISIBLE
                    binding.webviewBiblia.visibility = View.INVISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBarBiblia.visibility = View.GONE
                    binding.webviewBiblia.visibility = View.VISIBLE
                }
            }
            webChromeClient = WebChromeClient()

            // Configuración de máxima compatibilidad
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_DEFAULT
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

            loadUrl("https://www.bible.com/es-ES/bible/")
        }
    }

    // 4. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        // Manejar el historial de navegación del WebView
        if (binding.webviewBiblia.canGoBack()) {
            binding.webviewBiblia.goBack()
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
        return true
    }
}