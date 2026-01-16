package com.example.iglesiahosanna.subbotones.Formularios

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.iglesiahosanna.databinding.ActivityGruposconexionBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class gruposconexion : AppCompatActivity() {

    private lateinit var binding: ActivityGruposconexionBinding
    // Inicializar Firebase Firestore
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGruposconexionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        //sirve para bloquear el giro de pantallas
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Hacer que el enlace de la política de privacidad sea clickeable
        binding.textViewPrivacidad3.movementMethod = LinkMovementMethod.getInstance()

        // 1. Configurar la Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // 2. Habilitar el botón de regreso (flecha)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // 3. Configurar el listener para el botón de envío
        binding.btnEnviarDatosGrupoConexion.setOnClickListener {
            mostrarDialogoDeConfirmacion()
        }
    }

    private fun mostrarDialogoDeConfirmacion() {
        // Recoger los datos de los campos
        val nombre = binding.etNombre2.text.toString().trim()
        val telefono = binding.etLider2.text.toString().trim()
        val email = binding.etLider3.text.toString().trim()
        val edad = binding.etLider4.text.toString().trim()
        val EstadoCivil = binding.etLider5.text.toString().trim()

        // Validación simple
        if (nombre.isEmpty() || telefono.isEmpty() || edad.isEmpty() || EstadoCivil.isEmpty()) {
            Snackbar.make(binding.root, "Por favor, completa todos los campos requeridos", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Mostrar AlertDialog de confirmación
        AlertDialog.Builder(this)
            .setTitle("Confirmar Envío")
            .setMessage("¿Estás seguro de que quieres enviar tus datos?")
            .setPositiveButton("Confirmar") { _, _ ->
                enviarDatosAFirebase(nombre, telefono, email, edad, EstadoCivil)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun enviarDatosAFirebase(nombre: String, telefono: String, email: String, edad: String, estadoCivil: String) {
        // Deshabilitar el botón para evitar envíos múltiples
        binding.btnEnviarDatosGrupoConexion.isEnabled = false
        binding.btnEnviarDatosGrupoConexion.text = "Enviando..."

        // Crear un mapa con los datos del usuario
        val solicitud = hashMapOf(
            "nombre" to nombre,
            "telefono" to telefono,
            "email" to if (email.isNotEmpty()) email else null,
            "edad" to edad,
            "EstadoCivil" to estadoCivil,
            "timestamp" to FieldValue.serverTimestamp() // Añadir marca de tiempo
        )

        // Enviar los datos a la colección "solicitudes_grupos_conexion"
        db.collection("solicitudes_grupos_conexion")
            .add(solicitud)
            .addOnSuccessListener {
                // Éxito: mostrar mensaje y cerrar la actividad
                Snackbar.make(binding.root, "¡Gracias! Datos enviados con éxito", Snackbar.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                // Error: mostrar mensaje y habilitar el botón de nuevo
                Toast.makeText(this, "Error al enviar: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                binding.btnEnviarDatosGrupoConexion.isEnabled = true
                binding.btnEnviarDatosGrupoConexion.text = "Enviar Datos"
            }
    }

    // Manejar el evento de clic en el botón de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}