package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class FormularioMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_materia)

        val inputTextCodigo = findViewById<EditText>(R.id.input_codigo)
        val inputTextNombre = findViewById<EditText>(R.id.input_nombre)
        val inputTextCredito = findViewById<EditText>(R.id.input_creditos)
        val inputTextAula = findViewById<EditText>(R.id.input_aula)

        val btnCrearMateria = findViewById<Button>(R.id.btn_guardar_materia)
        btnCrearMateria.setOnClickListener {
            if ( BaseDatos.tablaMateria != null  ){
                BaseDatos.tablaMateria!!.crearMateria(
                    inputTextCodigo.text.toString(),
                    inputTextNombre.text.toString(),
                    inputTextCredito.text.toString(),
                    inputTextAula.text.toString()
                )
                Log.i("bdd","Creando Materias"  )
                this.finish()
                startActivity(Intent(this,MateriaActivity::class.java))
            }
        }
    }


    fun abrirActividad(
        clase: Class<*>
    ){
        val intendExplicito= Intent(
            this,
            clase
        )
        this.startActivity(intendExplicito);
    }

}