package com.mcmoviles.manuel.appburbujas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*

class listActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //declaracion del array
        val paises = arrayOf(
            "ARGENTINA",
            "CHILE",
            "PARAGUAY",
            "BOLIVIA",
            "PERU",
            "ECUADOR",
            "BRASIL",
            "COLOMBIA",
            "VENEZUELA",
            "URUGUAY"
        )
        val habitantes = arrayOf(
            40_000_000,
            17_000_000,
            6_500_000,
            10_000_000,
            30_000_000,
            14_000_000,
            183_000_000,
            44_000_000,
            31_000_000,
            3_500_000
        )

        //declaramos el adaptador
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises)
        var i:Int
        list1.adapter = adaptador1
       // list1.setOnItemClickListener(AdapterView.OnItemClickListener())
    }
}
