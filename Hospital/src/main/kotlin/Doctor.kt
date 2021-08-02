import java.util.*

class Doctor {
    var id: String = ""
    var cedula: String = ""
    var nombre: String = ""
    var telefono: Int = 0
    var direccion: String = ""
    var sueldo: Double = 0.0
    var fechaNacimiento: Date = Date()
    var casado: Boolean = true

    fun setDoctor() {
        println("Ingrese id:")
        this.id = readLine().toString()
        println("Ingrese cédula")
        this.cedula = readLine().toString()
        println("Ingrese nombre")
        this.nombre = readLine().toString()
        println("Ingrese número de telefono")
        this.telefono = readLine().toString().toInt()
        println("Ingrese dirección")
        this.direccion = readLine().toString()
        println("Ingrese ingrese el sueldo")
        this.sueldo = readLine().toString().toDouble()
        println("Ingrese la fecha de nacimimento DD/MM/AAAA")
        var fecha = readLine().toString()
        this.fechaNacimiento = Date(fecha)
        println("Ingrese estado civil TRUE O FALSE")
        this.casado = readLine().toString().toBoolean()
    }

    override fun toString(): String {
        return "\n Doctor(id='$id', cedula='$cedula', nombre='$nombre'," +
                " telefono=$telefono, direccion='$direccion', sueldo=$sueldo," +
                " fechaNacimiento=$fechaNacimiento, casado=$casado)\n"
    }
}