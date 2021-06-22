package com.example.android_edi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
    }
}