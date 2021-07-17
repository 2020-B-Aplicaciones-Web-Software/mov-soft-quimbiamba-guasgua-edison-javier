import java.util.*

class Doctor (
    private var id: String,
    private val nombre: String,
    private val fechaNacimiento: Date,
    private val correoElectronico: String,
    private val telefono: String,
    private val direccion: String,
    private val salario: Double,
    private val activo: Boolean
    ){

    override fun toString(): String {
        return "Doctor(id=$id, nombre='$nombre', fechaNacimiento=$fechaNacimiento, correoElectronico='$correoElectronico', Telefono='$telefono', direccion='$direccion', salario=$salario, Activo=$activo)"
    }

}