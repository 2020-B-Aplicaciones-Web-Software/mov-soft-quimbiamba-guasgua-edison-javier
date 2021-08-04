package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormularioActualizarMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_actualizar_materia)

        val idItem = intent.getParcelableExtra<MateriaBDD>("id")
        Log.i("bdd", "Vale : ${idItem?.id}")
        BaseDatos.tablaMateria = SQLiteHelperEstudiante(this)

        val codigo = findViewById<EditText>(R.id.input_codigoMateria)
        val nombre = findViewById<EditText>(R.id.input_nombreMateria)
        val creditos = findViewById<EditText>(R.id.input_creditosMateria)
        val aula = findViewById<EditText>(R.id.input_aulaMateria)



        codigo.setText(idItem!!.codigo)
        nombre.setText(idItem!!.nombre)
        creditos.setText(idItem!!.credito.toString())
        aula.setText(idItem!!.aula)

        val btnActualizarMateria = findViewById<Button>(R.id.btn_actualizar)

        btnActualizarMateria.setOnClickListener {

            if (BaseDatos.tablaMateria != null) {
                BaseDatos.tablaMateria!!.actualizarMateria(
                    idItem!!.id,
                    codigo.text.toString(),
                    nombre.text.toString(),
                    creditos.text.toString().toInt(),
                    aula.text.toString()
                )

                abrirActividad(MateriaActivity::class.java)
                Toast.makeText(this,"Se actualizado una materia", Toast.LENGTH_SHORT).show()

                Log.i("bdd", "Materia actualizada")
            }


        }


    }


    fun abrirActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito);
    }

}







