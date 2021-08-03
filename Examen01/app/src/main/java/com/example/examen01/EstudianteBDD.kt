package com.example.examen01

import android.os.Parcel
import android.os.Parcelable
import java.util.*
class EstudianteBDD(

    var idMateria: Int,
    var numeroUnico: String,
    var cedula: String,
    var nombre: String,
    var carrera:  String,
    var fechaNacimiento: String,
    var estado: Boolean
):Parcelable{
    constructor(parcel:Parcel): this (
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte()!= 0.toByte()
            ){
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idMateria)
        parcel.writeString(numeroUnico)
        parcel.writeString(cedula)
        parcel.writeString(nombre)
        parcel.writeString(carrera)
        parcel.writeString(fechaNacimiento)
        parcel.writeByte(if (estado) 1 else 0)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EstudianteBDD> {
        override fun createFromParcel(parcel: Parcel): EstudianteBDD {
            return EstudianteBDD(parcel)
        }

        override fun newArray(size: Int): Array<EstudianteBDD?> {
            return arrayOfNulls(size)
        }
    }
}