package com.example.firebasedos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebasedos.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

        val botonSalir = findViewById<Button>(R.id.btn_salir )
        botonSalir.setOnClickListener {
            solicitarSalirDelaplicativo()
        }

        val botonProducto = findViewById<Button>(R.id.btn_producto)
        botonProducto.setOnClickListener {
            irProducto()
        }
        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener {
           val intent = Intent ( this,
            DRestauante:: class.java)
            startActivity(intent)
        }
    }

    fun irProducto() {
        val intent = Intent(
            this,
            CProducto::class.java
        )
        startActivity(intent)
    }

    fun llamarLoginUsuario() {
        val providers = arrayListOf(
            //LISTA  DE PROVEEDORES
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                ).build(),
            CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if (usuario != null) {
                        if (usuario.isNewUser == true) {
                            Log.i("firebase-login", "Nuevo usuario")
                            registrarUsuarioPorPrimeraVez(usuario)
                        } else {
                            Log.i("firebase-login", "Usuario antiguo")
                            setearUsuario()
                        }
                    } else {
                        Log.i("firebase-login", "El usuario cancelo")
                    }
                }
            }
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse) {
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .currentUser
        if (usuario.email != null && usuarioLogeado != null) {
            //roles: ["usuario", "Admin"]
            // uid
            val db = Firebase.firestore//obtenemos  la referencia
            val rolesUsuario = arrayListOf("usuario") // creamos el arreglo de permisos
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsuario,
                "uid" to usuarioLogeado.uid,
                "email" to usuario.email.toString()
            )
            val identificadorUsuario = usuario.email   // Seleccionamos  que el identificador del usuario  sea  el correof

            db.collection("usuario").document(identificadorUsuario.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("fire-firestore", "Se creo")
                    setearUsuario()
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "fallo")
                }
        }
    }

    //instancia  de la autenticación luego  obtenemos el usuario local, si es que el usuario  local es diferene  de nulo entonces ahi vamos a empezar a  trabajar

    fun setearUsuario(){
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser
        if( usuarioLocal != null){
            if(usuarioLocal.email != null){
                val  db = Firebase.firestore
                val referencia = db.collection("usuario")
                    .document(usuarioLocal.email.toString())
                referencia.get().addOnSuccessListener {
                    val usuarioCargado =  it.toObject(FirestoreUsuarioDto:: class.java)//
                    if(usuarioCargado!=null) {
                        BAuthUsuario.usuario = BUsuarioFirebase(
                            usuarioCargado.uid,
                            usuarioCargado.email,
                            usuarioCargado.roles,
                        )
                        setBienvenida()
                    }
                    Log.i("firebase-firestore","usuario cargado")
                }.addOnSuccessListener {
                    Log.i("firebase-firestore","usuario cargado")
                }
            }
        }
    }


    fun setBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonSalir = findViewById<Button>(R.id.btn_salir)
        val botonProducto = findViewById<Button>(R.id.btn_producto)
        val botonRestauante = findViewById<Button>(R.id.btn_restaurante)


        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido  ${
                BAuthUsuario.usuario?.email
            }"
            botonLogin.visibility= View.INVISIBLE
            botonSalir.visibility= View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestauante.visibility = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingrese al aplicativo"
            botonLogin.visibility= View.VISIBLE
            botonSalir.visibility= View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE // le hacemos invisibles  a los botones de
            botonRestauante.visibility = View.INVISIBLE
        }
    }
    fun solicitarSalirDelaplicativo(){   // solicitamos  salir  del aplicativo
        AuthUI.getInstance().signOut(this).addOnCompleteListener{
            BAuthUsuario.usuario= null
            setBienvenida()


        }
    }

}



