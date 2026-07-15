package com.jdca.proyectofinal.UI_Buscadores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.UI_Buscadores.InfoBuscarAulaActivity
import com.jdca.proyectofinal.R

class BuscarAulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_buscaraula)

        val btnVolver = findViewById<Button>(R.id.btnVolverBuscarAula)
        val btnBuscar = findViewById<Button>(R.id.btnBuscarAula)
        val txtCodigo = findViewById<TextInputEditText>(R.id.txtCodAula)

        btnVolver.setOnClickListener {
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
            finish()
        }

        btnBuscar.setOnClickListener {
            val codigoIngresado = txtCodigo.text?.toString()?.trim()?.uppercase() ?: ""

            if (codigoIngresado.isEmpty()) {
                Toast.makeText(this, "Ingrese un código válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DBHelper(this)
            val aula = dbHelper.buscarAulaPorCodigo(codigoIngresado)

            if (aula != null) {
                val intent = Intent(this, InfoBuscarAulaActivity::class.java).apply {
                    putExtra("codigo", aula.codAula)
                    putExtra("pabellon", aula.pabellon)
                    putExtra("piso", aula.piso)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "No se encontró el aula", Toast.LENGTH_SHORT).show()
            }
        }
    }
}