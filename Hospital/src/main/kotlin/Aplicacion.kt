
import java.security.Principal
import java.util.*

fun main() {
    menuPrincipal()
//    val doctor = Doctor(id = 1819,nombre = "Edison Quimbiamba", direccion = "Dorado",fechaNacimiento = Date(),
//    correoElectronico = "Ed@gmail.com",
//    salario = 28.5,activo = true,telefono = "09787432165")
    val hospital = Hospital("123", "EugenioEspejo", "Alameda")
}

fun menuPrincipal() {
    println("--Bienvenido al SISTEMA DE SALUD--")
    println("------------Presione--------------")
    println("1. Menu Hospitales")
    println("2. Menu Doctores")
    println("0. Salir")
    var respuesta = ""
    do {
        var respuesta = readLine()
        when (respuesta) {
            "1" -> {
                menuHospital()
            }
            "2" -> {
                menuDoctor()
            }
        }
    } while (respuesta != "0")
} //Fin menu principal

fun menuHospital() {
    println("--Ahora estas  Menú Hospital..")
    println("------------Presione--------------")
    println("1. Registrar nuevo Hospital")
    println("2. Modificar Hospital ")
    println("3. Eliminar Hospital")
    println("0. Regresar Menú principal ")
    var respuesta = ""
    do {
        var respuesta = readLine()
        when (respuesta) {
            "1" -> {
                println("Registros de Hospital ")
            }
            "2" -> {
                println("Modificar hospital ")
            }
            "3" -> {
                println("Eliminar Hospital")
            }
            else -> {
                println("Seleccione un Numero valido ! ........... ")
                menuHospital()
            }
        }
    } while (respuesta != "0")
} //Fin Menu hospital

fun menuDoctor() {
    println("--Ahora estas en Menú Doctores..")
    println("------------Presione--------------")
    println("1. Registrar nuevo Doctor")
    println("2. Modificar Doctor ")
    println("3. Eliminar Doctor")
    println("0. Regresar Menú principal")
    do {
        var respuesta = readLine()
        when (respuesta) {
            "1" -> {
                println("Registrar Doctor ")
                var respuestaRegistro = "0"
                val hospital = Hospital("123", "EugenioEspejo", "Alameda")
                do {
                    println("Restrar Doctor")
                    println("Ingrese id Doctor")
                    val idDoctor: String = readLine().toString()
                    println("Ingrese nombre")
                    val nombre = readLine().toString()
                    println("Ingrese Fecha Naciemiento [dd/mm/yyyy]")
                    val fechaNacimiento = readLine().toString()
                    println("Ingrese un correo electronico")
                    val correoElectronico = readLine().toString()
                    println("Ingrese numero de telefono")
                    val numerotelefono = readLine().toString()
                    println("Ingrese dirección ")
                    val direccion = readLine().toString()
                    val doctor = Doctor(
                        idDoctor, nombre,
                        Date(fechaNacimiento), correoElectronico, numerotelefono, direccion, salario = 250.00, true
                    )
                    print("Desea ingresar otro Doctor,   \n  1 si \n 0 No") // poner un when
                    hospital.registro(doctor)
                    var respuestaRegistro = readLine()
                } while (respuestaRegistro != "0")
//                hospital.nomina.forEach { doctor ->
//                    println("${doctor}")
              //  }
            }
            "2" -> {
                println("Modificar Doctor ")
            }
            "3" -> {
                println("Eliminar Doctor")
            }
            else -> {
                println("Seleccione un Numero valido ! ........... ")
                menuDoctor()
            }
        }
    } while (respuesta != "0")

} //FIN MENU DOCTOR

