package com.jdca.proyectofinal.AdminAulasLabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jdca.proyectofinal.Model.Aula
import com.jdca.proyectofinal.R

class AulaAdapter(
    private val listaAulas: MutableList<Aula>,
    private val onEditarClick: (Aula) -> Unit,
    private val onEliminarClick: (Aula) -> Unit
) : RecyclerView.Adapter<AulaAdapter.AulaViewHolder>() {

    inner class AulaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreAula: TextView = itemView.findViewById(R.id.txtNombreAula)
        val pabellon: TextView = itemView.findViewById(R.id.txtPabellon)
        val piso: TextView = itemView.findViewById(R.id.txtPiso)
        val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)

        fun bind(aula: Aula) {
            nombreAula.text = aula.codAula
            pabellon.text = "Pabellón: ${aula.pabellon}"
            piso.text = "Piso: ${aula.piso}"

            btnEditar.setOnClickListener { onEditarClick(aula) }
            btnEliminar.setOnClickListener { onEliminarClick(aula) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AulaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aula, parent, false)
        return AulaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AulaViewHolder, position: Int) {
        holder.bind(listaAulas[position])
    }

    override fun getItemCount(): Int = listaAulas.size

    fun actualizarLista(nuevaLista: List<Aula>) {
        listaAulas.clear()
        listaAulas.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}