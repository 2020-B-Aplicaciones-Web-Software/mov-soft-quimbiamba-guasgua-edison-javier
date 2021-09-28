package com.example.firebasedos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
                            setBienvenida()

                        } else {

                            Log.i("firebase-login", "Usuario antiguo")
                            setearUsuario()
                            setBienvenida()
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

            }
        }
    }

    fun setBienvenida (){
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido  ${
                BAuthUsuario.usuario?.email
            }"

        }else{
            textViewBienvenida.text = "Ingrese al aplicativo"
        }
    }
}