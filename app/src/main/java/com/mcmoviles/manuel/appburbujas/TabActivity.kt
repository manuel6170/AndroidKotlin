package com.mcmoviles.manuel.appburbujas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.mcmoviles.manuel.appburbujas.Adapters.ViewPagerAdapter
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentListLavadoras
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentFavoritos
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentPerfil
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentReservas
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.AddFragment(FragmentListLavadoras(),resources.getString(R.string.tabHome))
        viewPagerAdapter.AddFragment(FragmentFavoritos(),resources.getString(R.string.tabFavoritos))
        viewPagerAdapter.AddFragment(FragmentPerfil(),resources.getString(R.string.tabPerfil))
        viewPagerAdapter.AddFragment(FragmentReservas(),resources.getString(R.string.tabReservas))

        viewPagerId.adapter = viewPagerAdapter
        tabLayoutId.setupWithViewPager(viewPagerId)
    }

}
