package com.example.examen02
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class EstudianteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante)

        val btnCrearMateria = findViewById<Button>(R.id.btn_crear_estudiante)
        btnCrearMateria.setOnClickListener{
            Log.i("bdd","Creando Materias"  )
            this.finish()
            startActivity(Intent(this,FormularioEstudiante::class.java))

        }



        }








}
