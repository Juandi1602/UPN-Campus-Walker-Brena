package com.jdca.proyectofinal.UI_Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.UI_Login.MainActivity
import com.jdca.proyectofinal.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_register)

        val btnVolverRegistro = findViewById<Button>(R.id.btnVolverRegistro)
        val btnRegistrarseFinal = findViewById<Button>(R.id.btnRegistrar)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreoRegistro)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaRegistro)
        val db = DBHelper(this)

        btnVolverRegistro.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegistrarseFinal.setOnClickListener {
            val correo = txtCorreo.text.toString().trim()
            val contrasena = txtContrasena.text.toString().trim()

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val registrado = db.registrarUsuario(correo, contrasena)
                if (registrado) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}