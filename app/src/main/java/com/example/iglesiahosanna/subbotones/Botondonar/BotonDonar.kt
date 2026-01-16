package com.example.iglesiahosanna.subbotones.Botondonar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import coil.load
import com.example.iglesiahosanna.R
import com.example.iglesiahosanna.databinding.ActivityBotonDonarBinding
import com.google.android.material.snackbar.Snackbar

class BotonDonar : AppCompatActivity() {
    private lateinit var binding: ActivityBotonDonarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)

        binding = ActivityBotonDonarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imagenfrendas = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Donativos%20madrid%20tarjerta.jpg"
        binding.imageButtontarjeta.load(imagenfrendas){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagenefectivo = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Donativos%20madrid%20efectivo.jpg"
        binding.imageButtonefectivo.load(imagenefectivo){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagenofreiban = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Donativos%20madrid%20ofrendas.jpg"
        binding.imageButtonofrendaiban.load(imagenofreiban){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagendiezmo = "https://raw.githubusercontent.com/Pacinoss/Imagenes_Generales_Hosanna/refs/heads/main/Donativos%20madrid%20diezmos.jpg"
        binding.imageButtondiezmo.load(imagendiezmo){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }
        binding.imageButtonefectivo.setOnClickListener {
            Snackbar.make(binding.root, "Diregete a tu iglesia para donar en efectivo", Snackbar.LENGTH_SHORT).show()

        }
        binding.imageButtontarjeta.setOnClickListener {
            Snackbar.make(binding.root, "Diregete a tu iglesia para donar en tarjeta", Snackbar.LENGTH_SHORT).show()
        }



        binding.imageButtondiezmo.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("IBAN", "ES5521003923270200026895")
            clipboard.setPrimaryClip(clip)
            Snackbar.make(binding.root, "IBAN copiado al portapapeles", Snackbar.LENGTH_SHORT).show()
           // Toast.makeText(this, "IBAN copiado al portapapeles", Toast.LENGTH_SHORT).show()
        }

        binding.imageButtonofrendaiban.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("IBAN", "ES7921003923220200019821")
            clipboard.setPrimaryClip(clip)
            Snackbar.make(binding.root, "IBAN copiado al portapapeles", Snackbar.LENGTH_SHORT).show()
           // Toast.makeText(this, "IBAN copiado al portapapeles", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}
