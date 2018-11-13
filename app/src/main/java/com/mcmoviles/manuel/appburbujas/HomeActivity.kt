package com.mcmoviles.manuel.appburbujas

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentOne
import com.mcmoviles.manuel.appburbujas.Fragments.FragmentTwo
import kotlinx.android.synthetic.main.activity_main3.*

class HomeActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                createFragmentOne()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoritos -> {
                message.setText(R.string.title_dashboard)
                createFragmentTwo()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_perfil -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        createFragmentOne()
    }

    fun createFragmentOne(){
        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createFragmentTwo(){
        val transaction = manager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
