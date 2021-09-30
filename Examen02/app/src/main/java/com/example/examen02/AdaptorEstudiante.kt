package com.example.examen02

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.Dto.EstudianteDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AdaptorEstudiante(
    private val context: EstudianteActivity,
    private val listaEstudiante: ArrayList<EstudianteDto>,
    private val rcvEstudiate: RecyclerView


) : RecyclerView.Adapter<AdaptorEstudiante.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var codigoTextView: TextView = view.findViewById(R.id.txv_codigoMateria)
        var nombreTextView: TextView = view.findViewById(R.id.txv_nombre)
        var numeroUnicoTextView: TextView = view.findViewById(R.id.input_numeroUnico)
        var cedulaTextView: TextView = view.findViewById(R.id.txv_cedula)
        var carreraTextView: TextView = view.findViewById(R.id.txv_carrera)
        var fechaNacimientoTextView: TextView = view.findViewById(R.id.txv_fechaNacimiento)
        var estadoCheck: CheckBox = view.findViewById(R.id.chxb_estado)
        var layoutEstudiante = view.findViewById<ConstraintLayout>(R.id.vistaEstudiantes)

        init {
            layoutEstudiante.setOnClickListener {
                popUpMenus(it)
            }
        }

        private fun popUpMenus(v: View) {
            var registroEstudiante: (MutableList<DocumentSnapshot>)
            val listaEstudiante = ArrayList<EstudianteDto>()
            val db = Firebase.firestore

            val referenciaEstudiante = db.collection("estudiante")

            referenciaEstudiante
                .get()
                .addOnSuccessListener {
                    registroEstudiante = it.documents
                    registroEstudiante.forEach { iteracion ->
                        val objEstudiante = iteracion.toObject(EstudianteDto::class.java)
                        objEstudiante!!.uid = iteracion.id
                        objEstudiante.carrera = iteracion.get("carrera").toString()
                        objEstudiante.cedula = iteracion.get("cedula").toString()
                        objEstudiante.estado =
                            iteracion.get("estado").toString()
                        objEstudiante.fechaNacimiento = iteracion.get("fecha").toString()
                        objEstudiante.idMateria = iteracion.get("materia").toString()
                        objEstudiante.latitud = iteracion.get("latitud") as Double?
                        objEstudiante.longitud = iteracion.get("longitud") as Double?
                        objEstudiante.nombre = iteracion.get("nombre").toString()
                        objEstudiante.numeroUnico = iteracion.get("numeroUnico").toString()

                        listaEstudiante.add(objEstudiante)
                    }

                    val idItem =listaEstudiante[adapterPosition]
                    val popup = PopupMenu(context, v)
                    popup.inflate(R.menu.menu_estudiante)
                    popup.setOnMenuItemClickListener {
                        when (it.itemId) {
                            //Editar
                            /*R.id.menuEditarEstudiante -> {
                                val intentExplicito =
                                    Intent(context, EstudiantesFormularioActualizacion::class.java)
                                intentExplicito.putExtra("id", idItem)
                                context.startActivity(intentExplicito)
                                context.finish()
                                Toast.makeText(
                                    context,
                                    "Editar estudiante -> ${adapterPosition}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }*/


                            //Eliminar
                            R.id.menuEliminarEstudiante -> {

                                val builder = AlertDialog.Builder(context)
                                builder.setTitle("ALERTA !! \n Seguro quiere eliminar este estudiante?")
                                builder.setPositiveButton(
                                    "Aceptar",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        referenciaEstudiante.document(idItem.uid.toString())
                                            .delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    context,
                                                    "Estudiante eliminado -> ${adapterPosition}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                context.finish()
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        EstudianteActivity::class.java
                                                    )
                                                )
                                            }
                                    }
                                )

                                builder.setNegativeButton(
                                    "Cancelar", null
                                )

                                val dialog = builder.create()
                                dialog.show()
                                true
                            }

                            R.id.menuMapaEstudiante -> {
                                val intentExplicito =
                                    Intent(context, MapitaActivity::class.java)
                                intentExplicito.putExtra("id",idItem)
                                context.startActivity(intentExplicito)
                                context.finish()
                                Toast.makeText(
                                    context,
                                    "Abriendo Ubicación",
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }


                            else -> true

                        }
                    }
                    popup.show()
                }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptorEstudiante.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.lista_estudiantes,
            parent,
            false
        )

        return MyViewHolder(view)
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