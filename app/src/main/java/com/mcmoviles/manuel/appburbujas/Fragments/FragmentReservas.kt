package com.mcmoviles.manuel.appburbujas.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*
import com.mcmoviles.manuel.appburbujas.Adapters.RecyclerAdapter
import com.mcmoviles.manuel.appburbujas.Adapters.RecyclerAdapterReservas
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Class.Reserva

import com.mcmoviles.manuel.appburbujas.R
import kotlinx.android.synthetic.main.fragment_list_lavadoras.*
import kotlinx.android.synthetic.main.fragment_reservaciones.*

class FragmentReservas: Fragment() {
     lateinit var ref: DatabaseReference

    lateinit var myAdapter : RecyclerAdapterReservas
    lateinit var clave:String
    lateinit var contenedor: ViewGroup
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reservaciones,container,false)
        myAdapter = RecyclerAdapterReservas()
        contenedor = container!!
        ref = FirebaseDatabase.getInstance().getReference("reservaciones")
        var reservas= arrayListOf<String>()
        //consulta a FireBase para obtener reservas
        allDataFireBase()
        return view

    }

    fun allDataFireBase(){
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                var listReservas:MutableList<Reserva> = mutableListOf()
                if (p0.exists()){
                    for (h in p0.children){
                        var bundle = h.getValue(Reserva::class.java)
                        //reservas.add(bundle!!.direccion)
                        listReservas.add(bundle!!)
                    }
                    //val adaptador1 = ArrayAdapter<String>(container!!.context, android.R.layout.simple_list_item_1,reservas)
                    //view.findViewById<ListView>(R.id.list1).adapter = adaptador1
                    view!!.findViewById<RecyclerView>(R.id.recyclerViewIdReservas).layoutManager = GridLayoutManager(contenedor.context,1)
                    //recyclerViewIdF.layoutManager= GridLayoutManager(mContext,2)
                    myAdapter.RecyclerAdapterReservas( listReservas,contenedor.context)
                    view!!.findViewById<RecyclerView>(R.id.recyclerViewIdReservas).adapter = myAdapter
                }
            }


        })
    }
}