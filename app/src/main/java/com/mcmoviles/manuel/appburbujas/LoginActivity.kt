package com.mcmoviles.manuel.appburbujas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Class.Usuario
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var bundle = intent.extras
        var direccion = bundle["dir"].toString()
        editTxtDir.hint= direccion
        editTxtDir.isEnabled = false

        var dbManager = DbManager(this)
        //dbManager.deleteDataUsuario()
        var result = dbManager.getAllDataUsuario()
        if (result.moveToFirst()){
            //var id = result.getInt(result.getColumnIndex(Constantes.COLUM_ID))
            Toast.makeText(this,resources.getString(R.string.toastUsuarioRegistrado), Toast.LENGTH_SHORT).show()
            val inten = Intent(this,TabActivity::class.java)
            startActivity(inten)
            finish()

        }else{
            Toast.makeText(this,resources.getString(R.string.toastSinUsuarioRegistrado), Toast.LENGTH_SHORT).show()
        }

        //EN CASO DE QUE EL USUARIO NO EXISTA
        guardarUsuarioId.setOnClickListener {
            var nombre = editTxtNombre.text.toString()
            var telefono = editTxtCelular.text.toString()
            var identificacion = editTxtIdentificacion.text.toString()
            var dbManager = DbManager(this)
            var usuario= Usuario(nombre,telefono,identificacion,direccion)
            var result = dbManager.insertDataUsuario(usuario)
            if(result>0){Toast.makeText(this,resources.getString(R.string.toastGuardados), Toast.LENGTH_SHORT).show()}
            else{Toast.makeText(this,resources.getString(R.string.toastNoGuardados), Toast.LENGTH_SHORT).show()}
            val inten = Intent(this,TabActivity::class.java)
            startActivity(inten)
            finish()


        }




    }
}
