package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIrModuloMateriaActivity =
            findViewById<Button>(R.id.btn_modulo_materia)// /ir  al modulo de materias
        btnIrModuloMateriaActivity.setOnClickListener {
            abrirActividad(MateriaActivity::class.java)
        }

        val btnIrModuloEstudianteActivity = findViewById<Button>(R.id.btn_modulo_estudiante )
        btnIrModuloEstudianteActivity.setOnClickListener {
            abrirActividad(EstudianteActivity::class.java)
        }
    }
    private fun abrirActividad(clase: Class<*>) {
        val intentExplicito = Intent(this, clase) //this menciona la página actual
        this.startActivity(intentExplicito)
    }


}