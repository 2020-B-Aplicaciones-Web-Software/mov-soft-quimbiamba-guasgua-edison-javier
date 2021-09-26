package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MateriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        val btnCrearMateria = findViewById<Button>(R.id.btn_crear_materia)
        btnCrearMateria.setOnClickListener{
            Log.i("bdd","Creando Materias"  )
            this.finish()
            startActivity(Intent(this,FormularioMateria::class.java))

        }
    }
}