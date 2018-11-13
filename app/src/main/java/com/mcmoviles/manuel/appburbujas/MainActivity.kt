package com.mcmoviles.manuel.appburbujas

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import com.mcmoviles.manuel.appburbujas.Adapters.RecyclerAdapter
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.mcmoviles.manuel.appburbujas.Model.*


class MainActivity : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var lavList: MutableList<Lavadora>
    lateinit var myAdapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lavList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lavadoras")
        myAdapter = RecyclerAdapter()
        recyclerViewId.setHasFixedSize(true)
        recyclerViewId.layoutManager= GridLayoutManager(this,2)
        readFireBase()


    }

/*
    fun getListLavadoras():MutableList<Lavadora>{
        var listLavadoras:MutableList<Lavadora> = ArrayList()
        var dbManager = DbManager(this)
        var result = dbManager.getAllData()
        if (result.moveToFirst()){
            do{
                var id = result.getInt(result.getColumnIndex(Constantes.COLUM_ID))
                var marca = result.getString(result.getColumnIndex(Constantes.COLUM_MARCA))
                var cap = result.getString(result.getColumnIndex(Constantes.COLUM_CAP))
                var valHora =  result.getInt(result.getColumnIndex(Constantes.COLUM_VALOR_HORA))
                var img = result.getString(result.getColumnIndex(Constantes.COLUMN_IMAGEN))
                var lavadora = Lavadora(id,marca,cap,valHora,"estado",img)
                listLavadoras.add(lavadora)

            }while (result.moveToNext())


        }else{
            Toast.makeText(this,"Cursor sin valores", Toast.LENGTH_SHORT).show()
            listLavadoras.add(Lavadora(0,"Samsung","27lb",1000,"estado","https://i2.wp.com/www.clipset.net/wp-content/uploads/2011/04/LG-Smart-Diagnosis.jpg"))
            listLavadoras.add(Lavadora(1,"LG","33lb",2000,"estado","https://i2.wp.com/www.clipset.net/wp-content/uploads/2011/04/LG-Smart-Diagnosis.jpg"))


        }

        return listLavadoras

    }
*/
    fun readFireBase(){
        var id :String= ""
        var mar :String= ""
        var cap :String= ""
        var valHora :Int=0
        var estado :String= ""
        var imagen :String= ""
        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children){
                        var bundle = h.getValue(Lavadora::class.java)
                        id = bundle!!.id
                        mar = bundle.marca
                        cap = bundle!!.capacidad
                        valHora = bundle!!.valorHora
                        estado = bundle!!.estado
                        imagen = bundle!!.imagen
                        var myLavadora = Lavadora(id,mar,cap,valHora,estado,imagen)
                        lavList.add(myLavadora)
                    }
                    myAdapter.RecyclerAdapter( lavList,applicationContext)
                    recyclerViewId.adapter = myAdapter
                }
            }

        })


    }

     fun saveFireBase(view: View){
        val keyId= ref.push().key.toString()
        val lavadora = Lavadora(keyId,"Samsung","27lb",1000,"estado","https://i2.wp.com/www.clipset.net/wp-content/uploads/2011/04/LG-Smart-Diagnosis.jpg")
        ref.child(keyId).setValue(lavadora).addOnCompleteListener {
           Toast.makeText(this,"Lavadora guardada",Toast.LENGTH_SHORT).show()
        }
}
}
