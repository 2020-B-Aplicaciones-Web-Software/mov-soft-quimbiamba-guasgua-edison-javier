package com.example.examen01

import android.os.Parcel
import android.os.Parcelable

class MateriaBDD(
    var id : Int,
    var codigo:String,
    var nombre: String,
    var credito: Int,
    var aula: String,
):Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(codigo)
        parcel.writeString(nombre)
        parcel.writeInt(credito)
        parcel.writeString(aula)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<MateriaBDD> {
        override fun createFromParcel(parcel: Parcel): MateriaBDD {
            return MateriaBDD(parcel)
        }

        override fun newArray(size: Int): Array<MateriaBDD?> {
            return arrayOfNulls(size)
        }
    }


}