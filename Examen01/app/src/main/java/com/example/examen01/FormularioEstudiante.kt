package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class FormularioEstudiante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_estudiante)
        val inputIdMateriaText = findViewById<EditText>(R.id.input_idMateria)
        val inputNumeroUNicoText = findViewById<EditText>(R.id.input_numeroUnico)
        val inputcedulaText = findViewById<EditText>(R.id.input_cedula)
        val inputnombreText = findViewById<EditText>(R.id.input_nombre)
        val inputcarreraText = findViewById<EditText>(R.id.input_carrera)
        val inputfechaNacimientoText = findViewById<EditText>(R.id.input_fechaNacimiento)
        val inputestadoText = findViewById<EditText>(R.id.input_estado)
        val btnGuardarEstudiante = findViewById<Button>(
            R.id.btn_guardar_estudiante
        )
        btnGuardarEstudiante.setOnClickListener {
            if (BaseDatos.tablaEstudiante != null) {
                BaseDatos.tablaEstudiante!!.crearEstudiante(
                    inputIdMateriaText.text.toString(),
                    inputNumeroUNicoText.text.toString(),
                    inputcedulaText.text.toString(),
                    inputnombreText.text.toString(),
                    inputcarreraText.text.toString(),
                    inputfechaNacimientoText.text.toString(),
                    inputestadoText.text.toString()
                )
                Log.i("bdd", "Creando estudiante")
                this.finish()
                startActivity(Intent(this, EstudianteActivity::class.java))
            }
        }
    }






    fun abrirActividad(
        clase: Class<*>
    ) {
        val intendExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intendExplicito);
    }


}