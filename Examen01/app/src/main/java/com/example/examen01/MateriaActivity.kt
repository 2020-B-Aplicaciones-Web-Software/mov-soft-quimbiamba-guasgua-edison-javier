package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MateriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        val arregloNumeros = arrayListOf<Int>(1,2,3)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1  // una precarga de la parte visual
            ,arregloNumeros
        )

        val listViewMaterias = findViewById<ListView>(R.id.ltv_materias)
        listViewMaterias.adapter= adaptador
    }





}