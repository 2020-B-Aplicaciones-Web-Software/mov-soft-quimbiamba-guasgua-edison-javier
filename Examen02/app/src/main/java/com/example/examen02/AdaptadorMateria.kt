package com.example.examen02

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.Dto.EstudianteDto
import com.example.examen02.Dto.MateriaDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdaptadorMateria(
    private val listMateria: ArrayList<MateriaDto>,
    private val context: MateriaActivity,
    private val rcvMateria: RecyclerView

) : RecyclerView.Adapter<AdaptadorMateria.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView = view.findViewById(R.id.input_numeroUnico)
        var codigoTextView: TextView = view.findViewById(R.id.txv_codigoMateria)
        var aulaTextView: TextView = view.findViewById(R.id.txv_carrera)
        var creditosTextView: TextView = view.findViewById(R.id.txv_cedula)
        var layoutMateria: ConstraintLayout = view.findViewById(R.id.vistaMaterias)

        init {
            layoutMateria.setOnClickListener {
                menuContextual(it)
            }
        }

        private fun menuContextual(v: View) {

            var registroMateria: (MutableList<DocumentSnapshot>) // Tipo de variable de firebase
            var listaMateria = ArrayList<MateriaDto>()
            val db = Firebase.firestore
            val refereciaMateria = db.collection("materia")
            refereciaMateria.get().addOnSuccessListener {
                registroMateria = it.documents
                registroMateria.forEach { iteracion ->
                    val objMateria = iteracion.toObject(MateriaDto::class.java)
                    objMateria!!.uid = iteracion.id
                    objMateria.codigo = iteracion.get("codigo").toString()
                    objMateria.nombre = iteracion.get("nombre").toString()
                    objMateria.creditos = iteracion.get("creditos").toString().toInt()
                    objMateria.aula = iteracion.get("aula").toString()
                    listaMateria.add(objMateria)
                }

                val idItem =
                    listaMateria[adapterPosition] // Retorna el numero  de la posición que se encuentrar  el adaptado en ese momento
                //valor  que entraga  del que estoy dando  click
                val popMenu = PopupMenu(context, v)
                popMenu.inflate(R.menu.menu_materias)
                popMenu.setOnMenuItemClickListener {
                    when (it.itemId) {

                        R.id.menuEditarMateria -> {
                            val intetExplicito =
                                Intent(context, FormularioActualizarMateria::class.java)
                            intetExplicito.putExtra("id", idItem)
                            context.startActivity(intetExplicito)
                            context.finish()
                            Toast.makeText(context, "Editar Cliked", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id.menuEliminarMateria -> {
                            val builder = AlertDialog.Builder(context)
                            builder.setTitle("Atento oe")
                            builder.setTitle("Estas seguro de eliminar la materia")
                            builder.setPositiveButton(
                                "Si",
                                DialogInterface.OnClickListener { dialog, which ->
                                    refereciaMateria.document(idItem.uid.toString()).delete()
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                context,
                                                "Eliminar clicked-- ${adapterPosition}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            context.finish()
                                            context.startActivity(
                                                Intent(
                                                    context, MateriaActivity::class.java
                                                )

                                            )


                                        }
                                }
                            )
                            builder.setNegativeButton("Cancelar", null)
                            val dialog = builder.create()
                            dialog.show()
                            true
                        } else -> true
                    }
                }
                popMenu.show()

            }

        }


        /*private fun menuContextual(view: View) {
            BaseDatos.tablaMateria = SQLiteHelperEstudiante(context)
            var idItem = BaseDatos.tablaMateria!!.consultaMateria()[adapterPosition]
            val popMenu = PopupMenu(context,view)
            popMenu.inflate(R.menu.menu_materias)
            popMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    //EDITA
                    R.id.menuEditarMateria -> {
                        val intetExplicito = Intent( context,FormularioActualizarMateria:: class.java )
                        intetExplicito.putExtra("id",idItem)
                        context.startActivity(intetExplicito)
                        true
                    }
                    R.id.menuEliminarMateria ->{

                        val builder = AlertDialog.Builder(context)
                        builder.setTitle("Alerta")
                        builder.setTitle("Seguro desea eliminar la materia")
                        builder.setPositiveButton(
                            "Si",
                            DialogInterface.OnClickListener{ dialog,which->
                                BaseDatos.tablaMateria!!.eliminarMateriaPorCodigo(idItem.codigo)
                                context.finish()
                                context.startActivity(Intent(context,MateriaActivity::class.java))
                            }
                        )
                        builder.setNegativeButton(
                            "NO",null
                        )
                        val dialogo = builder.create()
                        dialogo.show()

                        true
                    }

                    //ELIMINA
                    else -> true
                }


            }
            popMenu.show()

        }*/


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
        holder.creditosTextView.text = materia.creditos.toString()
    }

    override fun getItemCount(): Int {
        return listMateria.size
    }

    fun iniciarRecyclerView(
        lista: ArrayList<EstudianteDto>,
        activity: EstudianteActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdaptorEstudiante(
            activity, lista, recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()
    }


}