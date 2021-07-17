package com.example.android_edi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {
    var numero =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        Log.i("ciclo-vida", "onCreate")

        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVida.setOnClickListener({
            aumetarNumero()
            actualizarNumeroPantalla()
        })

    }
    private fun actualizarNumeroPantalla() {
        val textViewACicloVida = findViewById<TextView>(R.id.txv_ciclo_vida)
        textViewACicloVida.text= numero.toString()

    }
    private fun aumetarNumero() {

        numero = numero +1
    }


    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }
}