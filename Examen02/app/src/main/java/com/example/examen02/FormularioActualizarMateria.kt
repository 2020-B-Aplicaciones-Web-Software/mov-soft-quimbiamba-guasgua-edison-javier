package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen02.Dto.MateriaDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FormularioActualizarMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_actualizar_materia)

        val idItem = intent.getParcelableExtra<MateriaDto>("id")
        Log.i("bdd", "Vale : ${idItem?.uid}")
        //BaseDatos.tablaMateria = SQLiteHelperEstudiante(this)
        val db = Firebase.firestore
        val referenciaMateria = db.collection("materia")

        val codigo = findViewById<EditText>(R.id.input_codigoMateria)
        val nombre = findViewById<EditText>(R.id.input_nombreMateria)
        val creditos = findViewById<EditText>(R.id.input_creditosMateria)
        val aula = findViewById<EditText>(R.id.input_aulaMateria)



        codigo.setText(idItem!!.codigo)
        nombre.setText(idItem!!.nombre)
        creditos.setText(idItem!!.creditos.toString())
        aula.setText(idItem!!.aula)

        val btnActualizarMateria = findViewById<Button>(R.id.btn_actualizar)

        btnActualizarMateria.setOnClickListener {

            if (referenciaMateria != null) {

                referenciaMateria.document(idItem.uid.toString()).update(
                    "codigo", codigo.text.toString(),
                    "nombre" , codigo.text.toString(),
                    "creditos",codigo.text.toString().toInt(),
                    "aula",aula.text.toString()
                )

                abrirActividad(MateriaActivity::class.java)
                Toast.makeText(this,"Se actualizado una materia", Toast.LENGTH_SHORT).show()

                Log.i("bdd", "Materia actualizada")
                this.finish()
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







