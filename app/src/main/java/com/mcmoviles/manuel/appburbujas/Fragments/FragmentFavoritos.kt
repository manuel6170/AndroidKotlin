package com.mcmoviles.manuel.appburbujas.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import com.mcmoviles.manuel.appburbujas.Adapters.RecyclerAdapter
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import com.mcmoviles.manuel.appburbujas.R

import kotlinx.android.synthetic.main.fragment_favoritos.*
import kotlinx.android.synthetic.main.fragment_list_lavadoras.*


class FragmentFavoritos : Fragment() {

    lateinit var ref: DatabaseReference
    lateinit var lavList: MutableList<Lavadora>
    lateinit var myAdapter : RecyclerAdapter
    lateinit var contenedor : ViewGroup
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)
        lavList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lavadoras")
        myAdapter = RecyclerAdapter()
        contenedor = container!!
        readFireBase(container!!.context)
        //Toast.makeText(container!!.context,"estas en favoritos", Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onResume() {
        super.onResume()
        readFireBase(contenedor!!.context)
    }

    fun readFireBase(mContext : Context){
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    recyclerFavoritos.layoutManager = GridLayoutManager(mContext,2)
                    myAdapter.RecyclerAdapter( getListLavadoras(mContext),mContext)
                    recyclerFavoritos.adapter = myAdapter
                }
            }
        })
    }

    fun getListLavadoras(mContext : Context):MutableList<Lavadora>{
        var listLavadoras:MutableList<Lavadora> = ArrayList()
        var dbManager = DbManager(mContext)
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
            Toast.makeText(mContext,resources.getString(R.string.toastSinGuardar), Toast.LENGTH_SHORT).show()

        }

        return listLavadoras

    }



}
