package com.mcmoviles.manuel.appburbujas.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.R
import com.mcmoviles.manuel.appburbujas.TimeActivity


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    var listaLavadoras : MutableList<Lavadora> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(listaLavadoras : MutableList<Lavadora>, context: Context){
        this.listaLavadoras= listaLavadoras
        this.context= context

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card_view,parent,false))
    }

    override fun getItemCount(): Int {
        return listaLavadoras.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaLavadoras.get(position)
        holder.bind(item,context)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,TimeActivity::class.java)
            var id = listaLavadoras.get(position).id
            var marca = listaLavadoras.get(position).marca
            var capacidad = listaLavadoras.get(position).capacidad
            var valHora = listaLavadoras.get(position).valorHora
            var imagen = listaLavadoras.get(position).imagen
            var estado = listaLavadoras.get(position).estado
            intent.putExtra("id",id.toString())
            intent.putExtra("marca", marca)
            intent.putExtra("capacidad", capacidad)
            intent.putExtra("valorHora", valHora)
            intent.putExtra("estado", valHora)
            intent.putExtra("img",imagen)
            this.context.startActivity(intent)
        }
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        //val id = view.findViewById(R.id.txtLavadoraId) as TextView
        val marca =  view.findViewById(R.id.txtViewMarcaId) as TextView
        val capacidad = view.findViewById(R.id.txtViewCapId) as TextView
        val valorHora = view.findViewById(R.id.txtViewNumHId) as TextView
        val imgLavadora = view.findViewById(R.id.imgViewLavadoraId) as ImageView




        fun bind(lavadora: Lavadora, context: Context){
            //id.text = lavadora.id.toString()
            marca.text = lavadora.marca
            capacidad.text = lavadora.capacidad
            valorHora.text = lavadora.valorHora.toString()
            //uso de gglide
            Glide.with(context).load(lavadora.imagen).into(imgLavadora)




        }
    }



}