package com.jdca.proyectofinal.UI_Buscadores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.UI_Buscadores.InfoBuscarLabActivity
import com.jdca.proyectofinal.R

class BuscarLabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_buscarlab)

        val btnVolverLab = findViewById<Button>(R.id.btnVolverBuscarLab)
        val btnBuscarLab = findViewById<Button>(R.id.btnBuscarLab)
        val txtCodigoLab = findViewById<TextInputEditText>(R.id.txtCodLab)

        btnVolverLab.setOnClickListener {
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
            finish()
        }

        btnBuscarLab.setOnClickListener {
            val codigoIngresado = txtCodigoLab.text?.toString()?.trim()?.uppercase() ?: ""

            if (codigoIngresado.isEmpty()) {
                Toast.makeText(this, "Ingrese un código válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DBHelper(this)
            val lab = dbHelper.buscarLaboratorioPorCodigo(codigoIngresado)

            if (lab != null) {
                val intent = Intent(this, InfoBuscarLabActivity::class.java).apply {
                    putExtra("codigo_lab", lab.codLaboratorio)
                    putExtra("pabellon_lab", lab.pabellon)
                    putExtra("piso_lab", lab.piso)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "No se encontró el laboratorio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}