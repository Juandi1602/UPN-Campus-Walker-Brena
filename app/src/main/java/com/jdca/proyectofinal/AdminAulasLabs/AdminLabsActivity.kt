package com.jdca.proyectofinal.AdminAulasLabs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jdca.proyectofinal.DB.DBHelper
import com.jdca.proyectofinal.Model.Laboratorio
import com.jdca.proyectofinal.R

class AdminLabsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var labAdapter: LabAdapter
    private lateinit var dbHelper: DBHelper
    private val listaLabs = mutableListOf<Laboratorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_adminlaboratorios)

        recyclerView = findViewById(R.id.recyclerView)
        dbHelper = DBHelper(this)

        labAdapter = LabAdapter(
            listaLabs,
            onEditarClick = { lab ->
                val intent = Intent(this, AdminLabsAgregarActivity::class.java)
                intent.putExtra("laboratorio", lab)
                startActivity(intent)
            },
            onEliminarClick = { lab ->
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar eliminación")
                builder.setMessage("¿Deseas eliminar el laboratorio ${lab.codLaboratorio}?")
                builder.setPositiveButton("Sí") { _, _ ->
                    val eliminado = dbHelper.eliminarLaboratorio(lab.codLaboratorio)
                    if (eliminado) {
                        listaLabs.remove(lab)
                        labAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Laboratorio eliminado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar", null)
                builder.show()
            }
        )

        recyclerView.adapter = labAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        cargarLaboratoriosDesdeDB()

        val btnAgregar: Button = findViewById(R.id.btnAgregarLab)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AdminLabsAgregarActivity::class.java)
            startActivity(intent)
        }

        val btnVolver: Button = findViewById(R.id.btnVolverLabsAdmin)
        btnVolver.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        cargarLaboratoriosDesdeDB()
    }

    private fun cargarLaboratoriosDesdeDB() {
        listaLabs.clear()
        listaLabs.addAll(dbHelper.obtenerTodosLosLaboratorios().sortedBy { it.codLaboratorio })
        labAdapter.notifyDataSetChanged()
    }
}
