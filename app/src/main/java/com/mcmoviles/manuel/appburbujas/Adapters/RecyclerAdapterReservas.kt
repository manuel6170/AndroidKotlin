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
import com.mcmoviles.manuel.appburbujas.Class.Reserva
import com.mcmoviles.manuel.appburbujas.R
import com.mcmoviles.manuel.appburbujas.TimeActivity


class RecyclerAdapterReservas: RecyclerView.Adapter<RecyclerAdapterReservas.ViewHolder>(){

    var listReservas : MutableList<Reserva> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapterReservas(listReservas : MutableList<Reserva>, context: Context){
        this.listReservas= listReservas
        this.context= context

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerAdapterReservas.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_view_reservas,parent,false))
    }

    override fun getItemCount(): Int {
        return listReservas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listReservas.get(position)
        holder.bind(item,context)
        holder.itemView.setOnClickListener {


        }
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        //val id = view.findViewById(R.id.txtLavadoraId) as TextView
        val lavdoraId =  view.findViewById(R.id.lavadoraIdCard) as TextView
        val nombreId = view.findViewById(R.id.nombreIdCard) as TextView
        val direccionId = view.findViewById(R.id.direccionIdCard) as TextView
        val totalPagarId = view.findViewById(R.id.totalPagarIdCard) as TextView
        val horaEnvioId = view.findViewById(R.id.horaEnvioIdCard) as TextView
        val estadoId = view.findViewById(R.id.estadoIdCard) as TextView

        fun bind(reserva: Reserva, context: Context){
            lavdoraId.text = reserva.idLavadora
            nombreId.text = reserva.idUsuario
            direccionId.text = reserva.direccion
            totalPagarId.text = reserva.totalPagar.toString()
            horaEnvioId.text =  reserva.horaEnvio
            estadoId.text = reserva.estado





        }
    }



}