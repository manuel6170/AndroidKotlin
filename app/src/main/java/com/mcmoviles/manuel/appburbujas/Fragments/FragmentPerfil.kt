package com.mcmoviles.manuel.appburbujas.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mcmoviles.manuel.appburbujas.Class.Usuario
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import com.mcmoviles.manuel.appburbujas.R

class FragmentPerfil : Fragment() {
lateinit var contenedor: ViewGroup
    lateinit var myView: View
    lateinit var usuario: Usuario
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
       contenedor=container!!
        updateData()
        return view
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    fun updateData(){

        var nombre=""
        var telefono =""
        var identificacion=""
        var dbManager= DbManager(contenedor!!.context)
        var result = dbManager.getAllDataUsuario()
        if (result!!.moveToFirst()){
            nombre = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_NOMBRE))
            telefono = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_TELEFONO))
            identificacion = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_IDENTIFICACION))
            usuario=Usuario(nombre,telefono,identificacion)
            //Toast.makeText(this,"EL USUARIO en SQL"+nombre, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(contenedor!!.context,resources.getString(R.string.toastNoGuardados), Toast.LENGTH_SHORT).show()
        }
        view!!.findViewById<EditText>(R.id.nombrePerfil).hint=nombre
        view!!.findViewById<EditText>(R.id.IdentificacionPerfil).hint= identificacion
        view!!.findViewById<EditText>(R.id.telefonoPerfil).hint=telefono
        view!!.findViewById<Button>(R.id.btnEditarUsuarioId).setOnClickListener {
            view!!.findViewById<EditText>(R.id.nombrePerfil).isEnabled=true
            view!!.findViewById<EditText>(R.id.IdentificacionPerfil).isEnabled=true
            view!!.findViewById<EditText>(R.id.telefonoPerfil).isEnabled=true
            view!!.findViewById<Button>(R.id.btnGuarUsuarioId).isEnabled=true
            view!!.findViewById<Button>(R.id.btnGuarUsuarioId).setOnClickListener {
                //actualizar datos
                var resultado = dbManager.updateDataUsuario(usuario)
                Toast.makeText(contenedor!!.context,resources.getString(R.string.toastDatosUpdate),Toast.LENGTH_SHORT).show()
            }

        }
    }
}