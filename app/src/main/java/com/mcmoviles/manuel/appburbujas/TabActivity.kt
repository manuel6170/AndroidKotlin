package com.mcmoviles.manuel.appburbujas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mcmoviles.manuel.appburbujas.Adapters.ViewPagerAdapter
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentListLavadoras
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentFavoritos
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentPerfil
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.AddFragment(FragmentListLavadoras(),"HOME")
        viewPagerAdapter.AddFragment(FragmentFavoritos(),"FAVORITOS")
        viewPagerAdapter.AddFragment(FragmentPerfil(),"PERFIL")

        viewPagerId.adapter = viewPagerAdapter
        tabLayoutId.setupWithViewPager(viewPagerId)
    }

}
