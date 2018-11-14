package com.mcmoviles.manuel.appburbujas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        guardarUsuarioId.setOnClickListener {
            val inten = Intent(this,TabActivity::class.java)
            startActivity(inten)


        }

        var dbManager = DbManager(this)
        var result = dbManager.getOneData("1")
        if (result.moveToFirst()){
            var id = result.getInt(result.getColumnIndex(Constantes.COLUM_ID))
            Toast.makeText(this,"DESDE SQLITE "+id, Toast.LENGTH_SHORT).show()


        }else{
            Toast.makeText(this,"Sin conexion a Base de Datos Interna", Toast.LENGTH_SHORT).show()

        }
    }
}
