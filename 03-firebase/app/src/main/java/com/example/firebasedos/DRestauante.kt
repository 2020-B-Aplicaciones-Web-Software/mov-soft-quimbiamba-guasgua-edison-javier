package com.example.firebasedos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestauante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestauante)

        val botonCrearRestaurante = findViewById<Button>( R.id.btn_crear_restaurante)
            botonCrearRestaurante.setOnClickListener {

                crearRestaurante()
            }



    }

    private fun crearRestaurante() {
        val editTextNombre = findViewById<EditText>(R.id.et_nombre_restaurante)
        val nuevoRestauante = hashMapOf<String,Any>(
            "nombre" to editTextNombre.text.toString()
        )
        val db = Firebase.firestore
        val referencia =  db.collection("restaurante")
        referencia.add(nuevoRestauante).addOnSuccessListener {
            editTextNombre.text.clear()
        }.addOnFailureListener{
        }
    }


}