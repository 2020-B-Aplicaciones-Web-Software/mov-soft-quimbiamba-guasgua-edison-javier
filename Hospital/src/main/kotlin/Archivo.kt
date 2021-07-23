import java.io.File

class Archivo {
    val path = "C:\\Users\\elefa\\OneDrive - Escuela Politécnica Nacional\\Escritorio\\Deber1_Moviles\\mov-soft-quimbiamba-guasgua-edison-javier\\Hospital\\src\\main\\kotlin\\Hospital.txt"

    fun write(text:String){
        File(path).appendText(text)
    }

}