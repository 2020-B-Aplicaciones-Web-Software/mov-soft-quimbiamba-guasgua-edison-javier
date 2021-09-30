package com.example.examen02
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.Dto.EstudianteDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudianteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante)
        val recyclerViewEstudiante = findViewById<RecyclerView>(R.id.rcv_estudiante)

        val btnCrearMateria = findViewById<Button>(R.id.btn_crear_estudiante)
        btnCrearMateria.setOnClickListener{
            Log.i("bdd","Creando Materias"  )
            this.finish()
            startActivity(Intent(this,FormularioEstudiante::class.java))
        }

        var registroEstudiante:(MutableList<DocumentSnapshot>)
        var listaEstudiante = ArrayList<EstudianteDto>()
        val db = Firebase.firestore
        val refereciaEstudiante =  db.collection("estudiante")
        refereciaEstudiante.get().addOnSuccessListener {
            registroEstudiante = it.documents
            registroEstudiante.forEach { iteracion ->
                var objEstudiante = iteracion.toObject(EstudianteDto::class.java)
                objEstudiante!!.uid = iteracion.id
                objEstudiante!!.carrera = iteracion.get("carrera").toString()
                objEstudiante!!.cedula = iteracion.get("cedula").toString()
                objEstudiante!!.estado = iteracion.get("estado").toString().toBoolean()
                objEstudiante!!.fechaNacimiento = iteracion.get("fecha").toString()
                objEstudiante!!.idMateria = iteracion.get("materia").toString()
                objEstudiante!!.latitud = iteracion.get("latitud") as Double?
                objEstudiante!!.longitud = iteracion.get("longitud") as Double?
                objEstudiante!!.nombre = iteracion.get("nombre").toString()
                objEstudiante!!.numeroUnico = iteracion.get("numeroUnico").toString()

                listaEstudiante.add(objEstudiante)

            }
            iniciarRecyclerView(listaEstudiante, this,recyclerViewEstudiante )

        }

        }

        fun iniciarRecyclerView(
            lista: ArrayList<EstudianteDto>,
            activity: EstudianteActivity,
            recyclerView: RecyclerView
        ){
            val adaptador = AdaptorEstudiante(
                activity,
                // this,
                lista,
                recyclerView
            )
            recyclerView.adapter = adaptador
            recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            adaptador.notifyDataSetChanged()

        }

}
