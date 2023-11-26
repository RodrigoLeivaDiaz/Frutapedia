package com.example.frutapedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FruitAdapter
    private var listfruit = mutableListOf<FruitsApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerfruit)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = FruitAdapter(listfruit,this)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = {
            navegarDetalle(it)
        }
        getListaFrutas()
    }

    private fun getListaFrutas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getListadoFrutas("fruit/all")
            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val listFrutas = response
                    listFrutas?.let {
                        listfruit.addAll(it.sortedBy { fruitsResponse -> fruitsResponse.id })
                    }
                    for (fruitsResponse in listfruit) {
                        when (fruitsResponse.name) {
                            "Banana" -> fruitsResponse.image = R.drawable.banana
                            "Orange" -> fruitsResponse.image = R.drawable.orange
                            "Strawberry"-> fruitsResponse.image = R.drawable.straw
                            "Pear"-> fruitsResponse.image = R.drawable.pear
                            "Tomato"-> fruitsResponse.image = R.drawable.tomato
                            "Apple"-> fruitsResponse.image = R.drawable.apple
                            "Cherry"-> fruitsResponse.image = R.drawable.cherry
                            "Pineapple"-> fruitsResponse.image = R.drawable.pineaple
                            "Raspberry"-> fruitsResponse.image = R.drawable.raspberry
                            "Watermelon"-> fruitsResponse.image = R.drawable.watermelon
                            else -> fruitsResponse.image = R.drawable.orange
                        }
                    }
                } else {
                    val error = call.errorBody().toString()
                    Log.e("error", error,)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun navegarDetalle(FruitsApi: FruitsApi) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("fruitsResponse", FruitsApi)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fruityvice.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}