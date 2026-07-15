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

class InfoBuscarAulaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_infobuscaraula)

        val txtCodAula = findViewById<TextView>(R.id.txtCodAula)
        val txtPabellonAula = findViewById<TextView>(R.id.txtPabellonAula)
        val txtPisoAula = findViewById<TextView>(R.id.txtPisoAula)

        val codigo = intent.getStringExtra("codigo")
        val pabellon = intent.getStringExtra("pabellon")
        val piso = intent.getStringExtra("piso")

        if (codigo != null && pabellon != null && piso != null) {
            txtCodAula.text = codigo
            txtPabellonAula.text = pabellon
            txtPisoAula.text = piso
        } else {
            Toast.makeText(this, "No se pudo mostrar la información del aula", Toast.LENGTH_LONG).show()
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