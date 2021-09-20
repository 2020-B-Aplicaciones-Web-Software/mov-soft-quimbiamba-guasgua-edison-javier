package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdaptorEstudiante(
    private val listaEstudiante: ArrayList<EstudianteBDD>,
    private val context: EstudianteActivity,
    private val rcvEstudiate: RecyclerView


): RecyclerView.Adapter<AdaptorEstudiante.MyViewHolder>()  {
     inner    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view) {
            var codigoTextView :TextView = view.findViewById(R.id.txv_codigoMateria)
            var nombreTextView : TextView = view.findViewById(R.id.txv_nombre)
            var numeroUnicoTextView : TextView = view.findViewById(R.id.input_numeroUnico)
            var cedulaTextView: TextView = view.findViewById(R.id.txv_cedula)
            var carreraTextView: TextView = view.findViewById(R.id.txv_carrera)
            var fechaNacimientoTextView: TextView = view.findViewById(R.id.txv_fechaNacimiento)
            var estadoCheck : CheckBox = view.findViewById(R.id.chxb_estado)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.lista_estudiantes,
            parent,
            false
        )
        return MyViewHolder(view )
    }
    override fun onBindViewHolder(holder: AdaptorEstudiante.MyViewHolder, position: Int) {
        val estudiante = listaEstudiante[position]
        holder.codigoTextView.text = estudiante.idMateria.toString()
        holder.nombreTextView.text=  estudiante.nombre
        holder.numeroUnicoTextView.text = estudiante.numeroUnico
        holder.cedulaTextView.text= estudiante.cedula
        holder.carreraTextView.text= estudiante.carrera
        holder.fechaNacimientoTextView.text= estudiante.fechaNacimiento
        holder.estadoCheck.text  = estudiante.estado.toString()
    }
    override fun getItemCount(): Int {
        return listaEstudiante.size
    }
}