package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorMateria(
    private val listMateria: ArrayList<MateriaBDD>,
    private val context: MateriaActivity,
    private val rcvMateria: RecyclerView

) : RecyclerView.Adapter<AdaptadorMateria.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView = view.findViewById(R.id.input_numeroUnico)
        var codigoTextView: TextView = view.findViewById(R.id.txv_codigoMateria)
        var aulaTextView: TextView = view.findViewById(R.id.txv_carrera)
        var creditosTextView: TextView = view.findViewById(R.id.txv_cedula)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.lista_materias,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val materia = listMateria[position]
        holder.codigoTextView.text = materia.codigo
        holder.nombreTextView.text = materia.nombre
        holder.aulaTextView.text = materia.aula
        holder.creditosTextView.text = materia.credito.toString()
    }

    override fun getItemCount(): Int {
        return listMateria.size
    }
}