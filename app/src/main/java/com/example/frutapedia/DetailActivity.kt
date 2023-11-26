package com.example.frutapedia

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var t_nombre: TextView
    private lateinit var t_imagen: ImageView
    private lateinit var t_caloria: TextView
    private lateinit var t_grasa: TextView
    private lateinit var t_azucar: TextView
    private lateinit var t_carbo: TextView
    private lateinit var t_proteina: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val fruta = intent.getParcelableExtra<FruitsApi>("fruitsResponse")
        t_nombre = findViewById(R.id.t_nombre)
        t_imagen = findViewById(R.id.t_imagen)
        t_caloria = findViewById(R.id.textViewcalorias)
        t_grasa = findViewById(R.id.textViewgrasa)
        t_azucar = findViewById(R.id.textViewAzucar)
        t_carbo = findViewById(R.id.textViewCarbo)
        t_proteina = findViewById(R.id.textViewProteinas)
        t_nombre.text = fruta?.name
        t_caloria.text = "Calorias :" +fruta?.nutricion?.calories
        t_grasa.text =   "Grasas :" +fruta?.nutricion?.fat
        t_azucar.text = "Azucar :" +fruta?.nutricion?.sugar
        t_carbo.text = "Carbohidratos :" +fruta?.nutricion?.carbohydrates
        t_proteina.text = "Proteinas :" +fruta?.nutricion?.protein
        Glide.with(this).load(fruta?.image).into(t_imagen)
    }
}
