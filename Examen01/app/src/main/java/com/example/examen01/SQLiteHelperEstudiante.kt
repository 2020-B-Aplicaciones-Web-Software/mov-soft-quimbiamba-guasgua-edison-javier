package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class SQLiteHelperEstudiante(
    context: Context
) : SQLiteOpenHelper(context, "moviles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaEstudiante =
            """
                CREATE TABLE ESTUDIANTE(
                idEstudiante INTEGER PRIMARY KEY AUTOINCREMENT,
                idMateria    INTEGER,    
                numeroUnicoEstudiante  not null UNIQUE,
                cedulaEstudiante  VARCHAR (10) not null UNIQUE,
                nombreEstudiante  VARCHAR(50)  not null,      
                carreraEstudiante VARCHAR(100) not null,
                fechaNacimiento VARCHAR(200)   not null,
                estadoEstudiante bit not null,
                CONSTRAINT FK_EST_MATERIA foreign key (idMateria) references materia(idMateria)
                )
            """.trimIndent()
        val scriptCrearTablaMateria = """
            create table MATERIA(
                idMateria       integer primary key autoincrement,
                codigoMateria   varchar(10)   not null UNIQUE,
                nombreMateria   varchar(50)   not null,
                creditosMateria int           not null,
                aulaMateria     varchar(30)   not null
            )
        """.trimIndent()
        db?.execSQL(scriptCrearTablaMateria)
        db?.execSQL(scriptCrearTablaEstudiante)
    }

    //////////////////////////////// CrearEstudiante
    fun crearEstudiante(
        idMateria: String,
        numeroUnico: String,
        cedulaEstudiante: String,
        nombreEstudiante: String,
        carreraEstudiante: String,
        fechaNacimiento: String,
        estadoEstudiante: Boolean,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idMateria", idMateria)
        valoresAGuardar.put("numeroUnicoEstudiante", numeroUnico)
        valoresAGuardar.put("cedulaEstudiante", cedulaEstudiante)
        valoresAGuardar.put("nombreEstudiante", nombreEstudiante)
        valoresAGuardar.put("carreraEstudiante", carreraEstudiante)
        valoresAGuardar.put("fechaNacimiento", fechaNacimiento)
        valoresAGuardar.put("estadoEstudiante", estadoEstudiante)
        val resuladoEscritura: Long
        resuladoEscritura = conexionEscritura.insert(
            "Estudiante",
            null,
            valoresAGuardar
        )
        conexionEscritura.close()
        return if (resuladoEscritura.toInt() == -1) false else true  // devuelve un 1 si se logra la agregación al la  base de datos

    }
    /////////////////////

    fun consultarEstudiantes(): ArrayList<EstudianteBDD> {
        val scriptUsuario =
            "SELECT idMateria, numeroUnicoEstudiante, cedulaEstudiante, nombreEstudiante, carreraEstudiante, fechaNacimiento, estadoEstudiante FROM ESTUDIANTE"
        val baseDatosLectura = readableDatabase
        val listaEstudiantes = arrayListOf<EstudianteBDD>()
        val resultadoConsulta = baseDatosLectura.rawQuery(
            scriptUsuario,
            null
        )
        val existeEstudiante = resultadoConsulta.moveToFirst() // movemos al primero
        if (existeEstudiante) {
            do {

                val idMateria = resultadoConsulta.getInt(0)
                val numeroUnico = resultadoConsulta.getString(1)
                val cedula = resultadoConsulta.getString(2)
                val nombreEstudiante = resultadoConsulta.getString(3)
                val carreraEstudiante = resultadoConsulta.getString(4)
                val fechaNacimiento = resultadoConsulta.getString(5)
                val estado = (resultadoConsulta.getInt(6)) > 0
                if (idMateria != null) {
                    listaEstudiantes.add(
                        EstudianteBDD(
                            idMateria,
                            numeroUnico,
                            cedula,
                            nombreEstudiante,
                            carreraEstudiante,
                            fechaNacimiento,
                            estado
                        )
                    )

                }
            } while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        baseDatosLectura.close()
        Log.i("bd", resultadoConsulta.toString())
        return listaEstudiantes
    }


    fun crearMateria(
        codigo: String,
        nombre: String,
        creditos: String,
        aula: String,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("codigoMateria", codigo)
        valoresAGuardar.put("nombreMateria", nombre)
        valoresAGuardar.put("creditosMateria", creditos)
        valoresAGuardar.put("aulaMateria", aula)

        val resultadoEscritura = conexionEscritura.insert(
            "Materia", null, valoresAGuardar
        )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultaMateria(): ArrayList<MateriaBDD> {
        val scriptConsultaMateria = "Select * from materia"
        val baseDatosLectura = readableDatabase
        val listaMateria = arrayListOf<MateriaBDD>()
        val resutadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaMateria, null
        )
        val existeMateria = resutadoConsultaLectura.moveToFirst()
        do {
            if (existeMateria) {
                val id = resutadoConsultaLectura.getInt(0)
                val codigo = resutadoConsultaLectura.getString(1)
                val nombre = resutadoConsultaLectura.getString(2)
                val creditos = resutadoConsultaLectura.getInt(3)
                val aula = resutadoConsultaLectura.getString(4)

                if (codigo != null) {

                    listaMateria.add(
                        MateriaBDD(
                            id,
                            codigo, nombre, creditos, aula
                        )
                    )
                }
            }
        }while (resutadoConsultaLectura.moveToNext())

        resutadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resutadoConsultaLectura.toString())
        return listaMateria
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")

    }


}