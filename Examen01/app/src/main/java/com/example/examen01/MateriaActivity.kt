package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView

class MateriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        val arregloNumeros = arrayListOf<Int>(1, 2, 3)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1  // una precarga de la parte visual
            , arregloNumeros
        )

        var materias = arrayListOf<MateriaBDD>()
        materias = consultarMaterias(materias)

       // materias.add(MateriaBDD(1, "151", "XD", 6, "A2"))

        val rcvMaterias = findViewById<RecyclerView>(R.id.rcv_materia)
        iniciarRecyclerView(materias, this, rcvMaterias)


        val btnIrFormularioMateriactivity = findViewById<Button>(R.id.btn_crear_materia)
        btnIrFormularioMateriactivity.setOnClickListener {
            abrirActividad(FormularioMateria::class.java)
            this.finish()
        }


    }


    fun iniciarRecyclerView(
        lista: ArrayList<MateriaBDD>,
        activity: MateriaActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdaptadorMateria(
            lista,
            activity,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(recyclerView)


    }


    private fun consultarMaterias(
        lista: ArrayList<MateriaBDD>
    ): ArrayList<MateriaBDD>{
        var listMateria = arrayListOf<MateriaBDD>()
        listMateria = lista
        BaseDatos.tablaMateria = SQLiteHelperEstudiante(this)
        if (BaseDatos.tablaMateria != null){

            listMateria = BaseDatos.tablaMateria!!.consultaMateria()
        }
        return listMateria
    }



    private fun abrirActividad(clase: Class<*>) {
        val intentExplicito = Intent(this, clase) //this menciona la página actual
        this.startActivity(intentExplicito)
    }


}