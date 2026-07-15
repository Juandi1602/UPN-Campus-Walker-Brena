package com.jdca.proyectofinal.UI_Buscadores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jdca.proyectofinal.UI_Buscadores.BuscarAulaActivity
import com.jdca.proyectofinal.UI_Buscadores.BuscarLabActivity
import com.jdca.proyectofinal.R
import com.jdca.proyectofinal.UI_Login.MainActivity

class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_menuprincipal)

        val btnVolver = findViewById<Button>(R.id.btnVolverMenuP)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnAula = findViewById<Button>(R.id.btnAula)
        btnAula.setOnClickListener {
            val intent = Intent(this, BuscarAulaActivity::class.java)
            startActivity(intent)
        }

        val btnLab = findViewById<Button>(R.id.btnLab)
        btnLab.setOnClickListener {
            val intent = Intent(this, BuscarLabActivity::class.java)
            startActivity(intent)
        }
    }
}