package com.example.iglesiahosanna.subbotones.Formularios

import android.content.ContentValues.TAG
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.iglesiahosanna.databinding.ActivityPeticionesBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class Peticiones : AppCompatActivity() {

    private lateinit var binding: ActivityPeticionesBinding
    // Inicializamos Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeticionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        // 2. Ajustar los márgenes para que no choquen con la barra de estado o de navegación

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.textViewPrivacidad.movementMethod = LinkMovementMethod.getInstance()

        binding.buttonenviar.setOnClickListener {
            val nombre = binding.editTextNombre.text.toString().trim()
            val peticion = binding.editTextPeticion.text.toString().trim()
            val telefono = binding.editTextTelefono.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()

            // Corregido: Primero validamos que los campos obligatorios no estén vacíos.
            if (nombre.isNotEmpty() && peticion.isNotEmpty()) {
                // Si son válidos, mostramos el diálogo de confirmación.
                mostrarDialogoConfirmacion(nombre, peticion, telefono, email)
            } else {
                Snackbar.make(binding.root, "Por favor, completa el nombre y la petición", Snackbar.LENGTH_SHORT).show()
            }
        }
/*          //MARK: Solicitar permiso de notificación
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
       //     val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, "FCM Token: $token")
        //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

 */

    }

    private fun mostrarDialogoConfirmacion(nombre: String, peticion: String, telefono: String, email: String) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar envío")
            .setMessage("¿Quieres enviar esta petición?")
            .setPositiveButton("Confirmar") { _, _ ->
                // Solo si el usuario confirma pulsando el botón, guardamos en Firebase.
                guardarEnFirestore(nombre, peticion, telefono, email)
            }
            .setNegativeButton("Cancelar", null) // El diálogo simplemente se cierra si cancela.
            .show()
    }

    private fun guardarEnFirestore(nombre: String, peticion: String, telefono: String, email: String) {
        // Creamos un mapa con los datos.
        val peticionData = hashMapOf(
            "nombre" to nombre,
            "peticion" to peticion,
            "telefono" to telefono,
            "email" to email,
            "fecha" to com.google.firebase.Timestamp.now() // Añadimos fecha automáticamente.
        )

        // Añadimos a la colección "peticiones".
        db.collection("peticiones")
            .add(peticionData)
            .addOnSuccessListener {
                Snackbar.make(binding.root, "Petición guardada, pronto nos pondremos en contacto contigo", Snackbar.LENGTH_LONG).show()
                limpiarCampos()
            }
            .addOnFailureListener { e ->
                Snackbar.make(binding.root, "Error al guardar: ${e.message}", Snackbar.LENGTH_LONG).show()
            }
    }

    private fun limpiarCampos() {
        binding.editTextNombre.text?.clear()
        binding.editTextPeticion.text?.clear()
        binding.editTextTelefono.text?.clear()
        binding.editTextEmail.text?.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
