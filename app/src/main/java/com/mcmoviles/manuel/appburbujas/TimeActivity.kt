package com.mcmoviles.manuel.appburbujas

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import kotlinx.android.synthetic.main.activity_time.*
import kotlinx.android.synthetic.main.dialog_custom.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity() {

    var urlLavadora:String?= null
    var myDialog: Dialog?=null
    var id: Int?=null
    var marca :String?= null
    var capacidad :String?= null
    var valHora :Int?=null
    var estado :String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        myDialog = Dialog(this)
        val bundle = intent.extras
        id = bundle["id"].toString().toInt()
        marca = bundle["marca"].toString()
        capacidad = bundle["capacidad"].toString()
        valHora = bundle["valorHora"].toString().toInt()
        urlLavadora = bundle["img"].toString()
        estado = bundle["estado"].toString()
        Glide.with(this).load(urlLavadora).into(imgLavadora)
        //AGREGAR A FAVORITOS AL DAR CLICK EN LA ESTRELLA
        imgStarId.setOnClickListener {
            var dbManager = DbManager(this)
            var lavadora = Lavadora(id!!,marca!!,capacidad!!,valHora!!,estado!!,urlLavadora!!)
            var result = dbManager.insertData(lavadora)
            if(result>0){
                Toast.makeText(this,"GUARDADOS", Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(this,"DATOS NO ALMACENADOS", Toast.LENGTH_SHORT).show()
            }
        }

        //EN CASO DE ELIMINAR DE FAVORTIOS
        imgDelete.setOnClickListener{
            var dbManager = DbManager(this)
            dbManager.deleteDataFavoritos(marca!!)
            Toast.makeText(this,"Eliminado de Favoritos",Toast.LENGTH_SHORT).show()
        }
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        editTxtFechaI.setText(currentDate)
        editTxtHoras.setOnClickListener {
            if(editTxtHoras.text !=null){
                var resultado = editTxtHoras.text.toString().toInt() * this!!.valHora!!
                txtViewTPagar.text = resultado.toString()
            }else
                txtViewTPagar.text = "$0"
        }

        btnAceptarTiempo.setOnClickListener {
            var nombre=""
            var telefono =""
            var identificacion=""
            //TRAEMOS LOS DATOS DEL USUARIO
            var dbManager= DbManager(this)
            var result = dbManager.getAllDataUsuario()
            if (result!!.moveToFirst()){
                nombre = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_NOMBRE))
                telefono = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_TELEFONO))
                identificacion = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_IDENTIFICACION))
                //Toast.makeText(this,"EL USUARIO en SQL"+nombre, Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"EL USUARIO DEBE REGISTRARSE", Toast.LENGTH_SHORT).show()
            }

            myDialog!!.setContentView(R.layout.dialog_custom)
            myDialog!!.findViewById<TextView>(R.id.txtViewNombreDialog).text= nombre
            myDialog!!.findViewById<TextView>(R.id.txttViewTelefonoDialog).text= telefono
            myDialog!!.findViewById<TextView>(R.id.txtViewIdentiDialog).text= identificacion
            myDialog!!.findViewById<Button>(R.id.btnCancelar).setOnClickListener { myDialog!!.dismiss()
                Toast.makeText(this,"Accion Cancelada",Toast.LENGTH_SHORT).show()}
            myDialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog!!.show()

        }
    }

}
