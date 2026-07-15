package com.jdca.proyectofinal.AdminAulasLabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jdca.proyectofinal.Model.Laboratorio
import com.jdca.proyectofinal.R

class LabAdapter(
    private val listaLaboratorios: MutableList<Laboratorio>,
    private val onEditarClick: (Laboratorio) -> Unit,
    private val onEliminarClick: (Laboratorio) -> Unit
) : RecyclerView.Adapter<LabAdapter.LabViewHolder>() {

    inner class LabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreLab: TextView = itemView.findViewById(R.id.txtNombreLab)
        val pabellon: TextView = itemView.findViewById(R.id.txtPabellon)
        val piso: TextView = itemView.findViewById(R.id.txtPiso)
        val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)

        fun bind(lab: Laboratorio) {
            nombreLab.text = lab.codLaboratorio
            pabellon.text = "Pabellón: ${lab.pabellon}"
            piso.text = "Piso: ${lab.piso}"

            btnEditar.setOnClickListener { onEditarClick(lab) }
            btnEliminar.setOnClickListener { onEliminarClick(lab) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_laboratorio, parent, false)
        return LabViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        holder.bind(listaLaboratorios[position])
    }

    override fun getItemCount(): Int = listaLaboratorios.size

    fun actualizarLista(nuevaLista: List<Laboratorio>) {
        listaLaboratorios.clear()
        listaLaboratorios.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}