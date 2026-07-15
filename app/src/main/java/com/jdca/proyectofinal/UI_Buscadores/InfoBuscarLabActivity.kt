package com.jdca.proyectofinal.UI_Buscadores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jdca.proyectofinal.UI_Buscadores.MapaActivity
import com.jdca.proyectofinal.R

class InfoBuscarLabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_infobuscarlab)

        val txtCodigo = findViewById<TextView>(R.id.txtCodAula)
        val txtPabellon = findViewById<TextView>(R.id.txtPabellonAula)
        val txtPiso = findViewById<TextView>(R.id.txtPisoAula)

        val codigo = intent.getStringExtra("codigo_lab") ?: ""
        val pabellon = intent.getStringExtra("pabellon_lab") ?: ""
        val piso = intent.getStringExtra("piso_lab") ?: ""

        if (codigo.isNotEmpty() && pabellon.isNotEmpty() && piso.isNotEmpty()) {
            txtCodigo.text = codigo
            txtPabellon.text = pabellon
            txtPiso.text = piso
        } else {
            Toast.makeText(this, "Faltan datos del laboratorio", Toast.LENGTH_SHORT).show()
            finish()
        }

        val btnVolver = findViewById<Button>(R.id.btnVolverBuscarAula)
        btnVolver.setOnClickListener {
            finish()
        }

        val btnPabellones = findViewById<Button>(R.id.btnPabellones)
        btnPabellones.setOnClickListener {
            startActivity(Intent(this, MapaActivity::class.java))
        }
    }
}