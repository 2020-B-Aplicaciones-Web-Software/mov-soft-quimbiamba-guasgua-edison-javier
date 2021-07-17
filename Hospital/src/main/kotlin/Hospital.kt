class Hospital {

    private val codigoHospital: String
    private val nombreHospital: String
    private val direccionHospital: String
    val nomina: MutableList<Doctor> = mutableListOf<Doctor>()
    constructor(codigoHospital: String, nombreHospital: String, direccionHospital: String) {
        this.codigoHospital = codigoHospital
        this.nombreHospital = nombreHospital
        this.direccionHospital = direccionHospital
    }


    fun registro(doctor: Doctor) {
        nomina.add(doctor)
    }

    fun buscarDoctor( doctor: Doctor){

    }

    fun ModificarDotor(){

    }

    fun eliminarDoctor(){

    }





    override fun toString(): String {
        return "Hospital(codigoHospital='$codigoHospital', nombreHospital='$nombreHospital', direccionHospital='$direccionHospital', nomina=$nomina)"
    }


}




