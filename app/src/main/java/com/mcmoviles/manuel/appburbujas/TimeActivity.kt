package com.mcmoviles.manuel.appburbujas

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.mcmoviles.manuel.appburbujas.Class.Lavadora
import com.mcmoviles.manuel.appburbujas.Class.Reserva
import com.mcmoviles.manuel.appburbujas.Class.Usuario
import com.mcmoviles.manuel.appburbujas.Model.Constantes
import com.mcmoviles.manuel.appburbujas.Model.DbManager
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity() {
    lateinit var urlLavadora:String
    lateinit var myDialog: Dialog
    var id: Int?= null
    lateinit var marca :String
    lateinit var capacidad :String
    var valHora :Int?=null
    lateinit var estado :String
    lateinit var ref: DatabaseReference
    lateinit var usuario: Usuario
    lateinit var lavadora: Lavadora
    lateinit var nombre: String
    lateinit var telefono :String
    lateinit var identificacion: String
    lateinit var reserva: Reserva
    var totalPagar: Int?=null
    var horasUso: Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        myDialog = Dialog(this)
        ref= FirebaseDatabase.getInstance().getReference("reservaciones")
        // RECUPERANDO PARAMETROS DE LA LAVADORA
        val bundle = intent.extras
        id = bundle["id"].toString().toInt()
        marca = bundle["marca"].toString()
        capacidad = bundle["capacidad"].toString()
        valHora = bundle["valorHora"].toString().toInt()
        urlLavadora = bundle["img"].toString()
        estado = bundle["estado"].toString()
        Glide.with(this).load(urlLavadora).into(imgLavadora)
        lavadora = Lavadora(id!!,marca!!,capacidad!!,valHora!!,estado!!,urlLavadora!!)

        //AGREGAR A FAVORITOS AL DAR CLICK EN LA ESTRELLA
        imgStarId.setOnClickListener {
            var dbManager = DbManager(this)
            var result = dbManager.insertData(lavadora)
            if(result>0){Toast.makeText(this,"GUARDADOS", Toast.LENGTH_SHORT).show()}
            else{Toast.makeText(this,"DATOS NO ALMACENADOS", Toast.LENGTH_SHORT).show()}        }

        //EN CASO DE ELIMINAR DE FAVORTIOS
        imgDelete.setOnClickListener{
            var dbManager = DbManager(this)
            dbManager.deleteDataFavoritos(marca!!)
            Toast.makeText(this,"Eliminado de Favoritos",Toast.LENGTH_SHORT).show()}

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        editTxtFechaI.setText(currentDate)

        //CALCULAR EL TOTAL A PAGAR
        editTxtHoras.setOnClickListener {if(editTxtHoras.text !=null){
            horasUso = editTxtHoras.text.toString().toInt()
                totalPagar = horasUso!!* this!!.valHora!!
                txtViewTPagar.text = totalPagar.toString()
            }else txtViewTPagar.text = "$0"
        }

        //BOTON ACEPTAR MUESTRA EL DIALOGO
        btnAceptarTiempo.setOnClickListener {
            //TRAEMOS LOS DATOS DEL USUARIO
            var dbManager= DbManager(this)
            var result = dbManager.getAllDataUsuario()
            if (result!!.moveToFirst()){
                nombre = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_NOMBRE))
                telefono = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_TELEFONO))
                identificacion = result!!.getString(result!!.getColumnIndex(Constantes.COLUMN_IDENTIFICACION))
                usuario = Usuario(nombre,telefono,identificacion)
            }else{ Toast.makeText(this,"EL USUARIO DEBE REGISTRARSE", Toast.LENGTH_SHORT).show()
            }
            myDialog!!.setContentView(R.layout.dialog_custom)
            myDialog!!.findViewById<TextView>(R.id.txtViewNombreDialog).text= nombre
            myDialog!!.findViewById<TextView>(R.id.txttViewTelefonoDialog).text= telefono
            myDialog!!.findViewById<TextView>(R.id.txtViewIdentiDialog).text= identificacion
            myDialog!!.findViewById<Button>(R.id.btnCancelar).setOnClickListener { myDialog!!.dismiss()
            Toast.makeText(this,"Accion Cancelada",Toast.LENGTH_SHORT).show()}
            myDialog!!.findViewById<Button>(R.id.btnEnviar).setOnClickListener {
                //Insertar datos en firebase
                val horaEnvio = sdf.format(Date())
                var keyId = ref.push().key.toString()
                val random = Random().nextInt(100000000).toString()
                reserva=Reserva(random,lavadora.id.toString(),usuario.identificacion,totalPagar!!,horasUso!!,"carrea 17 # 60","enviada",horaEnvio)
                ref.child(random).setValue(reserva).addOnCompleteListener {
                    Toast.makeText(this,"Reservacion Guardada",Toast.LENGTH_SHORT).show()
                    myDialog!!.dismiss()
                }
            }
            val mp = MediaPlayer.create(this,R.raw.alerta2)
            mp.start()
            myDialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog!!.show()

            var cadena: String=""
/*
            ref.addValueEventListener(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()){
                        for (h in p0.children){
                            //var bundle = h.getValue(Reservacion::class.java)
                            var bundle = h.getValue(Usuario::class.java)
                            cadena = bundle!!.nombre
                            Toast.makeText(this@TimeActivity,"desde Fire : "+cadena,Toast.LENGTH_SHORT).show()

                        }

                    }
                }

            })
*/

        }

    }

}
