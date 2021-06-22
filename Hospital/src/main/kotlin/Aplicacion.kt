
import java.security.Principal
import java.util.*

fun main(){
    menuPrincipal()
//    val doctor = Doctor(id = 1819,nombre = "Edison Quimbiamba", direccion = "Dorado",fechaNacimiento = Date(),
//    correoElectronico = "Ed@gmail.com",
//    salario = 28.5,activo = true,telefono = "09787432165")
//    val hospital = Hospital("123","EugenioEspejo","Alameda",)
//    hospital.registro(doctor)
//    println(hospital)
}
fun menuPrincipal(){
    println("--Bienvenido al SISTEMA DE SALUD--")
    println("------------Presione--------------")
    println("1. Menu Hospitales")
    println("2. Menu Doctores")
    println("0. Salir")
    var respuesta = ""
    do {
        var respuesta = readLine()
        when(respuesta){
            "1" -> {
                menuDoctor()
            }
            "2" ->{
                menuHospital()
            }
        }
    }while(respuesta!="0" )
}
fun menuDoctor(){
    println("--Ahora estas  Menú Hospital..")
    println("------------Presione--------------")
    println("1. Registrar nuevo Hospital")
    println("2. Modificar Hospital ")
    println("3. Eliminar Hospital")
    println("0. Regresar Menú principal ")
}
fun menuHospital(){
    println("--Ahora estas en Menú Doctores..")
    println("------------Presione--------------")
    println("1. Registrar nuevo Doctor")
    println("2. Modificar Doctor ")
    println("3. Eliminar Doctor")
    println("0. Regresar Menú principal  ")
}
