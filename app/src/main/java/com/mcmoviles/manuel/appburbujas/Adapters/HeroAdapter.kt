package com.mcmoviles.manuel.appburbujas.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mcmoviles.manuel.appburbujas.Model.Hero
import com.mcmoviles.manuel.appburbujas.R
import kotlinx.android.synthetic.main.hero_layout.view.*

class HeroAdapter(val mCtx: Context, val layoutResId:Int, val heroList:List<Hero>):ArrayAdapter<Hero>(mCtx,layoutResId,heroList){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInfalter : LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInfalter.inflate(layoutResId,null)
        val textViewNameId = view.findViewById<TextView>(R.id.textViewName)
        val hero = heroList[position]
        textViewNameId.text = hero.name
        return view



    }

}