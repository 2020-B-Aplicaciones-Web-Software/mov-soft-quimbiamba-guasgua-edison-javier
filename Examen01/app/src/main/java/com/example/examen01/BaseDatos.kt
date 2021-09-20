package com.example.examen01

import android.database.sqlite.SQLiteOpenHelper

class BaseDatos {
    companion object{
        var tablaMateria: SQLiteHelperEstudiante?=null
        var tablaEstudiante: SQLiteHelperEstudiante?= null
    }
}