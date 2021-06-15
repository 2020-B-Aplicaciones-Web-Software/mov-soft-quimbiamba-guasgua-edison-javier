import java.util.*
import kotlin.collections.ArrayList

fun  main() {
    println("HOLA MUNDO")


    var edadProfesor: Int = 31
    var  sueldoProfesor: Double = 12.1
    // Mutables
    var edadCachorro =0
    edadCachorro=1
    edadCachorro=2
    edadCachorro=3

    //INMUTABLES
    val numeroCedula = 17126381849


    // Tipos de variables(Son los mismos tipo de variables que se tienen en JAVA)
    val nombreAlumno: String = "Edison Quimbiamba"
    val sueldo: Double = 15.5
    val estadoCivil: Char ='S'
    val fechaNacimiento: Date =Date()
    val casado: Boolean = false
    // Cualquier tipo de variable que usemos en java  ya lo podemos usar aki

    //Condicionales//

    when( estadoCivil){
        'C'-> println("huir")
        'S'-> println("Conversar ")
        'N' -> println("Nada")
        else -> {
            println("No tiene estado civil ")
        }
    }
    val sueldoMayorEstablecido  = if(sueldo > 12.2) 500 else 0
    println(sueldoMayorEstablecido)

    //Funciones

    imprimitNombre("Edison Quimbiamba")
    calcularSueldo(100.00, 15.00)
    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00,14.00,25)

    calcularSueldo(sueldo
    = 15.00,bono=16,tasa=15.0)

    //Arreglos estáticos y dinamicos

    val arregloEstatico:Array<Int> = arrayOf(1,2,3) // nosotros no podemos aumentar y disminuir elemetos

    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)

    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)

    println("Arreglo modificado ${arregloDinamico}")



    //OPERADORES  --> Sirven para los arreglos estaticos y dinámicos

    val respuestaForEach = arregloDinamico.forEach { valorActual -> println("Valor actual ${valorActual}") }

    arregloDinamico.forEach{println("valor actual ${it}")}

    //Cualquier operador tiene su mismo  operador index

    arregloDinamico.forEachIndexed{
        indice, valoractual ->
        println("valor actural ${valoractual}, indice actual ${indice}")
    }


    //Lo que nos permite es mutar el arreglo
    // Mao -> List <....>  nos devuelve  el arrego mutado
    //Enviemos el nuevo valor de la iteración
    // Nos devuelve es un nuevo arreglo con los valores modificados

    val respuestaMap  = arregloDinamico.map {
        valorActual -> return@map valorActual.toDouble() + 100.00
    }

    println(respuestaMap)

    val respuestaFechas = arregloDinamico.map {
        valorActual -> return@map Date()

    }

    println(respuestaFechas) //

    //filter --Nos ayuda a filtrar los elementos del arreglo


    val respuestaFilter = arregloDinamico.filter {
        valorActual->
        val mayoresACinco = valorActual>5
        return@filter mayoresACinco //Bolean
    }

    println(respuestaFilter)





}// FIN MAIN

    fun imprimitNombre(nombre:String) {
        println("Nombre ${nombre}") // Template String

    }

    fun calcularSueldo(
        sueldo: Double, //Parametro requerido los que se usan normalmente
        tasa: Double = 12.00, //opcionales al tener un valor por defecto es opcional
        bono: Int? = null  // variables que pueden ser nulas
    ):Double {
        if (bono != null) {
            return sueldo * (100 / tasa) + bono

        }else{
            return  sueldo*(100/tasa)
        }
    }




