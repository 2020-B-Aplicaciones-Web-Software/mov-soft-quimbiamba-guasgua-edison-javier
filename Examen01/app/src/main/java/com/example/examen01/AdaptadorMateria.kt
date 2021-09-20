package com.example.examen01

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
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
        var layoutMateria: ConstraintLayout = view.findViewById(R.id.vistaMaterias)

        init {
            layoutMateria.setOnClickListener{
                menuContextual(it)
            }
        }

        private fun menuContextual(view: View) {
            BaseDatos.tablaMateria = SQLiteHelperEstudiante(context)
            var idItem = BaseDatos.tablaMateria!!.consultaMateria()[adapterPosition]
            val popMenu = PopupMenu(context,view)
            popMenu.inflate(R.menu.menu_materias)
            popMenu.setOnMenuItemClickListener {

                when(it.itemId){
                    //EDITA CTM
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

        }


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