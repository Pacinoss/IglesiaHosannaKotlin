package com.example.iglesiahosanna.subbotones.Formularios

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.iglesiahosanna.databinding.ActivityTestimoniosBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class Testimonios : AppCompatActivity() {

    private lateinit var binding: ActivityTestimoniosBinding
    // Inicializamos Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestimoniosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación
        // Esto hace que la app se adapte a cualquier tipo de pantalla (con notch, botones, etc.)

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.textViewPrivacidad2.movementMethod = LinkMovementMethod.getInstance()



        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configurar el botón de envío
        binding.btnEnviarTestimonio.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val testimonio = binding.etTestimonio.text.toString().trim()
            val lider = binding.etLider.text.toString().trim()

            // Validamos que los campos obligatorios no estén vacíos.
            if (nombre.isNotEmpty() && testimonio.isNotEmpty()) {
                // Si son válidos, mostramos el diálogo de confirmación.
                mostrarDialogoConfirmacion(nombre, testimonio, lider)
            } else {
                Snackbar.make(binding.root, "Por favor, completa tu nombre y el testimonio", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarDialogoConfirmacion(nombre: String, testimonio: String, lider: String) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar envío")
            .setMessage("¿Quieres enviar este testimonio?")
            .setPositiveButton("Confirmar") { _, _ ->
                // Solo si el usuario confirma pulsando el botón, guardamos en Firebase.
                guardarEnFirestore(nombre, testimonio, lider)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun guardarEnFirestore(nombre: String, testimonio: String, lider: String) {
        // Creamos un mapa con los datos.
        val testimonioData = hashMapOf(
            "nombre" to nombre,
            "testimonio" to testimonio,
            "lider" to lider,
            "fecha" to com.google.firebase.Timestamp.now() // Añadimos fecha automáticamente.
        )

        // Añadimos a la colección "testimonios".
        db.collection("testimonios")
            .add(testimonioData)
            .addOnSuccessListener {
                Snackbar.make(binding.root, "Testimonio enviado con éxito, ¡gracias por compartir!", Snackbar.LENGTH_LONG).show()
                limpiarCampos()
            }
            .addOnFailureListener { e ->
                Snackbar.make(binding.root, "Error al enviar: ${e.message}", Snackbar.LENGTH_LONG).show()
            }
    }

    private fun limpiarCampos() {
        binding.etNombre.text?.clear()
        binding.etTestimonio.text?.clear()
        binding.etLider.text?.clear()
    }

    // 3. Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
