package com.jdca.proyectofinal.AdminAulasLabs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jdca.proyectofinal.R
import com.jdca.proyectofinal.UI_Login.MainActivity

class AdminMenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_adminmenuprincipal)

        val btnVolver = findViewById<Button>(R.id.btnVolverAdminMenu)
        val btnAulas = findViewById<Button>(R.id.btnAdminAbrirAula)
        val btnLaboratorios = findViewById<Button>(R.id.btnAdminAbrirLab)

        btnVolver.setOnClickListener {
            // Vuelve a la pantalla inicial
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAulas.setOnClickListener {
            // Abre gestión de aulas
            val intent = Intent(this, AdminAulasActivity::class.java)
            startActivity(intent)
        }

        btnLaboratorios.setOnClickListener {
            // Abre gestión de laboratorios
            val intent = Intent(this, AdminLabsActivity::class.java)
            startActivity(intent)
        }
    }
}