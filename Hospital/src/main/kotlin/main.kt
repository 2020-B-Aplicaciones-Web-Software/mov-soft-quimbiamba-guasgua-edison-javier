fun main() {
    //menuPrincipal()
    val doctor = Doctor()
    val listaHospital = ListaHospital()
    val hospital = Hospital()
    menuPrincipal(hospital, listaHospital)
}


fun menuPrincipal(hospital: Hospital, listaHospital: ListaHospital) {
    var option = ""
    println("---BIENVENIDO  AL SISTEMA DE HOSPITALES")
    println("1.  Hospitales")
    println("2. Doctores")
    println("3. Guardar cambios")
    println("0. Salir del sistema")
    option = readLine().toString()
    when (option) {
        "1" -> {
            menuHospitales(hospital, listaHospital)


        }
        "2" -> {
            seleccionaHospital(hospital, listaHospital)
        }
        "3"->{
            val archivo = Archivo()
            archivo.write(listaHospital.toString())
            menuPrincipal(hospital,listaHospital)
        }
        else -> {
            println("Selecciona un numero valido")
        }
    }
}

fun menuHospitales(hospital: Hospital, listaHospital: ListaHospital) {
    var option = ""
    do {
        println("--MENU HOSPITALES")
        println("1.Registrar Hospitales")
        println("2.Modificar Hospital")
        println("3.Eliminar Hospital")
        println("0.Menú Principal")
        var option = readLine().toString()
        when (option) {
            "1" -> {
                println("REGISTRO DE HOSPITALES")
                val nuevoHospital = Hospital()
                nuevoHospital.setHospital()
                listaHospital.registrarHospital(nuevoHospital)
                println("Hospital registrado exitosamente ")
                println(listaHospital)
            }

            "2" -> {
                println("MODIFICAR DE HOSPITALES")
                println("SELECCIONE HOSPITAL QUE DESEA MODIFICAR")
                println(listaHospital.nomina)
                val id = readLine().toString()
                listaHospital.modificarHospital(id)
                menuHospitales(hospital, listaHospital)
            }

            "3" -> {
                println("ELIMINAR HOSPITAL")
                println(listaHospital.nomina)
                println("Ingrese el id del Hospital que desea eliminar")
                val id = readLine().toString()
                listaHospital.eliminarHospital(id)
                println("Hospital eliminado satisfactoriamente")
                println("${listaHospital.nomina}")
                menuHospitales(hospital, listaHospital)
            }

            else -> println("Seleccione una opción validad")
        }
    } while (option != "0")
    menuPrincipal(hospital,listaHospital)
}



    fun seleccionaHospital(hospital: Hospital, listaHospital: ListaHospital) {


        listaHospital.nomina.forEach {
            hospital->
            println("SELECCIONE  EL HOSPITAL QUE DESEA MODIFICAR !")
            println(listaHospital.nomina)
            val id = readLine().toString()
            if (hospital.id == id) {
                println("Ahora  te encuentras en el hospita ${hospital.nombre}")

                var option = ""
                do {
                    println("--MENU DOCTORES")
                    println("1.Registrar Doctores")
                    println("2.Modificar Doctores")
                    println("3.Eliminar Doctores")
                    println("0.Menú Principal")
                    option = readLine().toString()
                    when (option) {
                        "1" -> {
                            println("REGISTRO DE DOCTORES")
                            val nuevoDoctor = Doctor()
                            nuevoDoctor.setDoctor()
                            print(nuevoDoctor)
                            hospital.registrarDoctor(nuevoDoctor)
                            print(hospital)
                        }
                        "2" -> {
                            println("MODIFICAR DE DOCTORES")
                            println("Seleccione el ID  del doctor ")
                            val idDoctor = readLine().toString()
                            hospital.modificarDoctor(idDoctor)
                            seleccionaHospital(hospital, listaHospital)

                        }
                        "3" -> {
                            println("ELIMINAR DOCTORES")
                            println(hospital.nomina)
                            println("Ingrese el id del doctor que desea eliminar")
                            val id = readLine().toString()
                            hospital.eliminarDoctor(id)
                            print("Doctor  eliminado satisfactoriamente")
                            seleccionaHospital(hospital, listaHospital)
                        }
                        else -> println("Seleccione una opción validad")
                    }
                } while (option != "0")
                menuPrincipal(hospital,listaHospital)
            }
        }
    }
