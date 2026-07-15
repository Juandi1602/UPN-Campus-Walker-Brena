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
import com.jdca.proyectofinal.Model.Aula
import com.jdca.proyectofinal.R

class AdminAulasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var aulaAdapter: AulaAdapter
    private lateinit var dbHelper: DBHelper
    private val listaAulas = mutableListOf<Aula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_adminaulas)

        recyclerView = findViewById(R.id.recyclerView)
        dbHelper = DBHelper(this)

        aulaAdapter = AulaAdapter(
            listaAulas,
            onEditarClick = { aula ->
                val intent = Intent(this, AdminAulasAgregarActivity::class.java)
                intent.putExtra("aula", aula)
                startActivity(intent)
            },
            onEliminarClick = { aula ->
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar eliminación")
                builder.setMessage("¿Estás seguro de que deseas eliminar el aula ${aula.codAula}?")
                builder.setPositiveButton("Sí") { _, _ ->
                    val eliminado = dbHelper.eliminarAula(aula.codAula)
                    if (eliminado) {
                        listaAulas.remove(aula)
                        aulaAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Aula eliminada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar", null)
                builder.show()
            }
        )

        recyclerView.adapter = aulaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        cargarAulasDesdeDB()

        val btnAgregar: Button = findViewById(R.id.btnAgregarAula)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AdminAulasAgregarActivity::class.java)
            startActivity(intent)
        }

        val btnVolver: Button = findViewById(R.id.btnVolverAulasAdmin)
        btnVolver.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        cargarAulasDesdeDB()
    }

    private fun cargarAulasDesdeDB() {
        listaAulas.clear()
        listaAulas.addAll(dbHelper.obtenerTodasLasAulas().sortedBy { it.codAula })
        aulaAdapter.notifyDataSetChanged()
    }
}