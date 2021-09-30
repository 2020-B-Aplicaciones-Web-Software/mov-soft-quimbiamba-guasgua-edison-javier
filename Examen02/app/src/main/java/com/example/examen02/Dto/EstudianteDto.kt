package com.example.examen02.Dto

import android.os.Parcel
import android.os.Parcelable
import java.lang.StringBuilder

class EstudianteDto(
    var carrera:String? = null,
    var cedula: String? = null,
    var estado: String? = null,
    var fechaNacimiento:String?= null,
    var idMateria: String? = null,
    var latitud: Double? = null,
    var longitud: Double? = null,
    var nombre: String? = null,
    var numeroUnico: String?  = null,
    var uid: String? = null
):Parcelable{
    constructor(parcel:Parcel) : this (
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
            ){
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(carrera)
        parcel.writeString(cedula)
        parcel.writeString(estado)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(idMateria)
        parcel.writeDouble(latitud!!)
        parcel.writeDouble(longitud!!)
        parcel.writeString(nombre)
        parcel.writeString(numeroUnico)
        parcel.writeString(uid)
    }

    companion object CREATOR : Parcelable.Creator<EstudianteDto> {
        override fun createFromParcel(parcel: Parcel): EstudianteDto {
            return EstudianteDto(parcel)
        }
        override fun newArray(size: Int): Array<EstudianteDto?> {
            return arrayOfNulls(size)
        }
    }
}

