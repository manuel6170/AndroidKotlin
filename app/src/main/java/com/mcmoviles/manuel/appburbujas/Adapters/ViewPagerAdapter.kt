package com.mcmoviles.manuel.appburbujas.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    val fragmentList= mutableListOf<Fragment>()
    val fragmentListTitle= mutableListOf<String>()

    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentListTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitle.get(position)
    }

    fun AddFragment(fragment: Fragment, titulo:String){
        fragmentList.add(fragment)
        fragmentListTitle.add(titulo)
    }
}