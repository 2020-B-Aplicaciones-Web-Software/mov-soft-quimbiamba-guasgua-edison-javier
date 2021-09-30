package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.Dto.MateriaDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MateriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)

        val recyclerViewMateria = findViewById<RecyclerView>(R.id.rcv_materia)


        val btnCrearMateria = findViewById<Button>(R.id.btn_crear_materia)
        btnCrearMateria.setOnClickListener {
            Log.i("bdd", "Creando Materias")
            startActivity(Intent(this, FormularioMateria::class.java))
            this.finish()
        }

        var registroMateria: (MutableList<DocumentSnapshot>)
        val listaMateria = ArrayList<MateriaDto>()
        val db = Firebase.firestore
        val referenciaMateria = db.collection("materia")

        referenciaMateria.get().addOnSuccessListener {
            registroMateria = it.documents
            registroMateria.forEach { iteracion ->
                val objetoMateria = iteracion.toObject(MateriaDto::class.java)
                objetoMateria!!.uid = iteracion.id
                objetoMateria.codigo = iteracion.get("codigo").toString()
                objetoMateria.nombre = iteracion.get("nombre").toString()
                objetoMateria.aula = iteracion.get("aula").toString()
                objetoMateria.creditos = iteracion.get("creditos").toString().toInt()

                listaMateria.add(objetoMateria)
            }

            iniciarRecyclerView(listaMateria,this,recyclerViewMateria)

        }
    }
        fun iniciarRecyclerView(
            lista: ArrayList<MateriaDto>,
            activity: MateriaActivity,
            recyclerView: RecyclerView
        ){
            val adaptador = AdaptadorMateria(
                lista,
                activity,
                recyclerView
            )

            recyclerView.adapter = adaptador
            recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
            recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            adaptador.notifyDataSetChanged()
        }
    }