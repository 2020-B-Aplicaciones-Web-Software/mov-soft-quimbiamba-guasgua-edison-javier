package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
class SQLiteHelperEstudiante(
    context: Context
) : SQLiteOpenHelper(context, "moviles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaEstudiante =
            """
                CREATE TABLE ESTUDIANTE(
                idEstudiante INTEGER PRIMARY KEY AUTOINCREMENT,
                idMateria    INTEGER     
                numeroUnicoEstudiante  not null UNIQUE,                           ,
                cedulaEstudiante  VARCHAR (10) not null UNIQUE,
                nombreEstudiante  VARCHAR(50)  not null,      
                carreraEstudiante VARCHAR(100) not null,
                fechaNacimiento VARCHAR(200)   not null,
                estadoEstudiante VARCHAR(15)   bit not null,
                CONSTRAINT FK_EST_MATERIA foreign key (idMateria) references materia(idMateria)
                )
            """.trimIndent()
        val scriptCrearTablaMateria = """
            create table MATERIA(
                idMateria       integer primary key autoincrement,
                codigoMateria   varchar(10)   not null UNIQUE,
                nombreMateria   varchar(50)   not null,
                creditosMateria int           not null,
                aulaMateria     varchar(30)   not null,
      
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


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}