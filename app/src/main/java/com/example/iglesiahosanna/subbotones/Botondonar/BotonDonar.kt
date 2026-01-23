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

        val imagenfrendas = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Donativos%2FDonativos%20madrid%20tarjerta.jpg?alt=media&token=9bb77929-327a-4fdd-99da-64612ee0a429"
        binding.imageButtontarjeta.load(imagenfrendas){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagenefectivo = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Donativos%2FDonativos%20madrid%20efectivo.jpg?alt=media&token=1f298b28-50f9-4aeb-8aaf-b74177dbd19f"
        binding.imageButtonefectivo.load(imagenefectivo){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagenofreiban = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Donativos%2FDonativos%20madrid%20ofrendas.jpg?alt=media&token=11aa6f9f-b477-41ec-817c-96c0f2517d3f"
        binding.imageButtonofrendaiban.load(imagenofreiban){
            crossfade(false)
            placeholder(R.drawable.anuncio_placeholder)
        }

        val imagendiezmo = "https://firebasestorage.googleapis.com/v0/b/iglesia-hosanna-e9497.firebasestorage.app/o/Donativos%2FDonativos%20madrid%20diezmos.jpg?alt=media&token=7e748439-6d3f-40f0-8fcd-fe54057ca090"
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
