class Hospital {
    var id: String =""
    var nombre: String=""
    var direccion: String=""
    var numeroTelefono:String=""
    var nomina: MutableList<Doctor> = mutableListOf<Doctor>()

    fun setHospital(){
        println("Ingrese el codigo del hospital")
        this.id = readLine().toString()
        println("Ingrese el nombre del hospital")
        this.nombre = readLine().toString()
        println("Ingrese dirección del hospital")
        this.direccion = readLine().toString()
        println("Ingrese el numero de telefono")
        this.numeroTelefono = readLine().toString()
    }
    fun registrarDoctor(doctor: Doctor){
        nomina.add(doctor)
        println("Registro exitoso")
    }

    fun eliminarDoctor(id: String){
        val respuestafilter = nomina.filter {
             doctor -> doctor.id ==id
        }
        respuestafilter.forEach{
            doctor ->
            this.nomina.remove(doctor)
        }

    }
    fun modificarDoctor(id: String){

        this.nomina.forEach {
            doctor ->
            if(doctor.id==id){
                print("Haz selecciona al doctor ${doctor.nombre}")
                doctor.setDoctor()
                println("Datos modificados correctamente")
            }
        }
    }

    override fun toString(): String {
        return " \n Hospital(id='$id', nombre='$nombre', direccion='$direccion', numeroTelefono='$numeroTelefono', nomina=$nomina) \n "
    }


}