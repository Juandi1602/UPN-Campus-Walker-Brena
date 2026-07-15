package com.jdca.proyectofinal.AdminAulasLabs

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.Model.Aula
import com.jdca.proyectofinal.R

class AdminAulasAgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_adminagregaraulas)

        val txtCodigo = findViewById<TextInputEditText>(R.id.txtCodAula)
        val txtPabellon = findViewById<TextInputEditText>(R.id.txtPabellonAula)
        val txtPiso = findViewById<TextInputEditText>(R.id.txtPisoAula)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVolver = findViewById<Button>(R.id.btnVolverAgregar)

        val db = DBHelper(this)

        // Verifica si se recibió un aula para editar
        val aulaExistente = intent.getSerializableExtra("aula") as? Aula

        if (aulaExistente != null) {
            txtCodigo.setText(aulaExistente.codAula)
            txtPabellon.setText(aulaExistente.pabellon)
            txtPiso.setText(aulaExistente.piso)
            txtCodigo.isEnabled = false // Código no editable
            btnGuardar.text = "Actualizar"
        }

        btnGuardar.setOnClickListener {
            val codigo = txtCodigo.text.toString().trim()
            val pabellon = txtPabellon.text.toString().trim()
            val piso = txtPiso.text.toString().trim()

            if (codigo.isEmpty() || pabellon.isEmpty() || piso.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val aula = Aula(codigo, pabellon, piso)

            if (aulaExistente == null) {
                val exito = db.insertarAula(aula)
                if (exito) {
                    Toast.makeText(this, "Aula registrada correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Ya existe un aula con ese código", Toast.LENGTH_SHORT).show()
                }
            } else {
                val exito = db.actualizarAula(aula)
                if (exito) {
                    Toast.makeText(this, "Aula actualizada correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar el aula", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnVolver.setOnClickListener {
            finish()
        }
    }
}