package com.jdca.proyectofinal.UI_Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.UI_Buscadores.MenuPrincipalActivity
import com.jdca.proyectofinal.R
import com.jdca.proyectofinal.AdminAulasLabs.AdminMenuPrincipalActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val btnVolver = findViewById<Button>(R.id.btnVolverLogin)
        val btnIniciarSesion = findViewById<Button>(R.id.btnLoginIS)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaLogin)

        val dbHelper = DBHelper(this)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnIniciarSesion.setOnClickListener {
            val correo = txtCorreo.text.toString().trim()
            val contrasena = txtContrasena.text.toString().trim()

            if (correo == "Admin" && contrasena == "admin") {
                Toast.makeText(this, "Bienvenido administrador", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdminMenuPrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } else if (dbHelper.verificarUsuario(correo, contrasena)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
