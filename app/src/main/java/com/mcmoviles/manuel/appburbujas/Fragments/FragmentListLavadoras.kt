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
import com.mcmoviles.manuel.appburbujas.HomeActivity

import com.mcmoviles.manuel.appburbujas.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_one.*


class FragmentListLavadoras : Fragment() {

    lateinit var ref: DatabaseReference
    lateinit var lavList: MutableList<Lavadora>
    lateinit var myAdapter : RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        lavList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("lavadoras")
        myAdapter = RecyclerAdapter()
        readFireBase(container!!.context)
        Toast.makeText(container!!.context,"estas en lista fragment",Toast.LENGTH_SHORT).show()
        return view
    }

    fun readFireBase(mContext : Context){
        var id :Int= 0
        var mar :String= ""
        var cap :String= ""
        var valHora :Int=0
        var estado :String= ""
        var imagen :String= ""
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children){
                        var bundle = h.getValue(Lavadora::class.java)
                        id = bundle!!.id.toInt()
                        mar = bundle.marca
                        cap = bundle!!.capacidad
                        valHora = bundle!!.valorHora
                        estado = bundle!!.estado
                        imagen = bundle!!.imagen
                        var myLavadora = Lavadora(id,mar,cap,valHora,estado,imagen)
                        lavList.add(myLavadora)
                    }

                            recyclerViewIdF.layoutManager = GridLayoutManager(mContext,2)
                   myAdapter.RecyclerAdapter( lavList,mContext)
                   recyclerViewIdF.adapter = myAdapter
                }
            }

        })


    }




}
