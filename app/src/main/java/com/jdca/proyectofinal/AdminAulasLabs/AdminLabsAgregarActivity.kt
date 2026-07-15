package com.jdca.proyectofinal.AdminAulasLabs

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.Model.Laboratorio
import com.jdca.proyectofinal.R

class AdminLabsAgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_adminagregarlaboratorios)

        val txtCodigo = findViewById<TextInputEditText>(R.id.txtCodLab)
        val txtPabellon = findViewById<TextInputEditText>(R.id.txtPabellonAula)
        val txtPiso = findViewById<TextInputEditText>(R.id.txtPisoAula)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVolver = findViewById<Button>(R.id.btnVolverAgregar)

        val db = DBHelper(this)

        // Verifica si se recibió un laboratorio para editar
        val labExistente = intent.getSerializableExtra("laboratorio") as? Laboratorio

        if (labExistente != null) {
            txtCodigo.setText(labExistente.codLaboratorio)
            txtPabellon.setText(labExistente.pabellon)
            txtPiso.setText(labExistente.piso)
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

            val laboratorio = Laboratorio(codigo, pabellon, piso)

            if (labExistente == null) {
                val exito = db.insertarLaboratorio(laboratorio)
                if (exito) {
                    Toast.makeText(this, "Laboratorio registrado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Ya existe un laboratorio con ese código", Toast.LENGTH_SHORT).show()
                }
            } else {
                val exito = db.actualizarLaboratorio(laboratorio)
                if (exito) {
                    Toast.makeText(this, "Laboratorio actualizado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar el laboratorio", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
