package com.example.android_edi

<<<<<<< HEAD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
>>>>>>> desarrollo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD


        val botonIrACicloVida = findViewById<Button>(
            R.id.btn_ir_ciclo_vida
        )
        botonIrACicloVida.setOnClickListener({ abrirCicloVida()})
    }

     fun abrirCicloVida() {
        val intentExplicito = Intent(
            this,
            ACicloVida::class.java
        )
         startActivity(intentExplicito)
=======
>>>>>>> desarrollo
    }
}