class ListaHospital {

    var nomina: MutableList<Hospital> = mutableListOf<Hospital>()

    fun registrarHospital(hospital: Hospital){
        nomina.add(hospital)
        println("HOSPITAL REGISTRADO SATISFACTORIAMENTE")
    }
    fun eliminarHospital(id : String){
        val respuestaFilter: List<Hospital> =  nomina.filter {
            hospital -> hospital.id ==id
        }
        respuestaFilter.forEach{
           hospital ->
           this.nomina.remove(hospital)
        }
    }
    fun modificarHospital(id:String){
        this.nomina.forEach {
            hospital ->
            if(hospital.id==id){
                println("Haz seleccinado el Hospital  ${hospital.nombre}")
                hospital.setHospital()
                println("Datos  guardados correctamente")
            }
        }
    }
    fun buscarHospital(id: String){
        this.nomina.forEach {
            hospital ->
            if (hospital.id == id){
                println("Selecciona el Doctor que deseas modificar")
                val idDoc= readLine().toString()
            }
        }
    }


    override fun toString(): String {
        return "ListaHospital(nomina=$nomina)"
    }


}