package com.mcmoviles.manuel.appburbujas.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mcmoviles.manuel.appburbujas.R

class FragmentPerfil : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        Toast.makeText(container!!.context,"ESTOY EN PERFIL", Toast.LENGTH_SHORT).show()
        return view
    }
}