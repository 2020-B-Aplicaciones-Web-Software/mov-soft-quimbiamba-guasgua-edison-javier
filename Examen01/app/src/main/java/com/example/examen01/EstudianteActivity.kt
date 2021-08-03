package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
class EstudianteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante)
        var estudiante = arrayListOf<EstudianteBDD>()
        estudiante =consultarEstudiante(estudiante)
        var rcvEstudiante = findViewById<RecyclerView>(R.id.rcv_estudiante)
//        estudiante.add(
//            EstudianteBDD(
//                1,"201545","EdisonQuimbiamba",
//                "Software","1726381849", "17/15/12",true
//            )
//        )
        iniciaRecyclerView(estudiante,this,rcvEstudiante)
        val btnIrformularioEstudiante = findViewById<Button>(R.id.btn_crear_estudiante)
        btnIrformularioEstudiante.setOnClickListener {
            abrirActividad(FormularioEstudiante::class.java)
            this.finish()
        }
    }

    fun iniciaRecyclerView(
        lista: ArrayList<EstudianteBDD>,
        activity: EstudianteActivity,
        recyclerView: RecyclerView
    ){

        val adaptador = AdaptorEstudiante(
            lista,
            activity,
            recyclerView

        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

    }

    private fun consultarEstudiante(
        lista: ArrayList<EstudianteBDD>
    ): ArrayList<EstudianteBDD>{
        var listEstudiante= arrayListOf<EstudianteBDD>()
        BaseDatos.tablaEstudiante= SQLiteHelperEstudiante(
            this
        )
        if (BaseDatos.tablaEstudiante != null){
            listEstudiante = BaseDatos.tablaEstudiante!!.consultarEstudiantes()
        }
        return listEstudiante
    }
    private fun abrirActividad(clase: Class <*>){
        val intentExplicito = Intent( this, clase)
        this.startActivity(intentExplicito)
    }
}