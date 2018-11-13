package com.mcmoviles.manuel.appburbujas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import com.mcmoviles.manuel.appburbujas.Adapters.HeroAdapter
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Model.Hero
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
lateinit var ref: DatabaseReference
    lateinit var heroList: MutableList<Hero>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        listView = findViewById(R.id.listViewId)
        heroList = mutableListOf()
        val ref = FirebaseDatabase.getInstance().getReference("heroes")
        var nombre :String= ""
        var iden:String=""
        var edad=0
        //heroList.add(Hero("id1","mama",34))
        //heroList.add(Hero("id2","papa",40))
        //val adapter = HeroAdapter(applicationContext,R.layout.hero_layout, heroList)
        //listView.adapter = adapter

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                for (h in p0.children){
                    var bundle = h.getValue(Hero::class.java)

                    nombre = bundle!!.name
                    iden = bundle!!.id
                    edad = bundle!!.edad

                    var hero = Hero(iden,nombre,edad)
                    heroList.add(hero)

                }
                    val adapter = HeroAdapter(applicationContext,R.layout.hero_layout, heroList)
                    listView.adapter = adapter
            }
            }
        })

    }


    fun saveFareBase(){

        val ref = FirebaseDatabase.getInstance().getReference("heroes")
        val heroId= ref.push().key.toString()
        val hero = Hero(heroId,"murillo",34)
                ref.child(heroId).setValue(hero).addOnCompleteListener {
            Toast.makeText(this,"heroe guardada", Toast.LENGTH_SHORT).show()
        }
    }
}
