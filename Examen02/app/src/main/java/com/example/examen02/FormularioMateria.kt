package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FormularioMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_materia)

        val btnGuardarMateria =  findViewById<Button>(R.id.btn_guardar_materia)
        btnGuardarMateria.setOnClickListener {
            guardarMateria()

        }
    }
    private fun guardarMateria() {
        //Ingreso de datos
        val editTextCodigo = findViewById<EditText>(R.id.input_codigo)
        val editTextNombre = findViewById<EditText>(R.id.input_nombre)
        val editTextCreditos = findViewById<EditText>(R.id.input_creditos)
        val editTextAula = findViewById<EditText>(R.id.input_aula)

        //ahora si accedemos a la base de datos
        //llenando la base de dato
        val nuevaMateria = hashMapOf<String,Any>(
            "codigo" to editTextCodigo.text.toString(),
            "nombre" to editTextNombre.text.toString(),
            "creditos" to editTextCreditos.text.toString().toInt(),
            "aula" to editTextAula.text.toString()
        )

        val db = Firebase.firestore
        val referencia = db.collection("materia") //nombre  de la colección de firebase
        referencia.add(nuevaMateria).addOnSuccessListener {
            editTextCodigo.text.clear() //limpiar la pantalla
            editTextNombre.text.clear()
            editTextCreditos.text.clear()
            editTextAula.text.clear()

            Toast.makeText( this, "Has ingresedo un materia ve :v ",Toast.LENGTH_SHORT).show()
            this.finish()
        }.addOnFailureListener{
            Toast.makeText(this, "Algo  salio mal mmmv", Toast.LENGTH_SHORT).show()
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

