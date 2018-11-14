package com.mcmoviles.manuel.appburbujas.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.mcmoviles.manuel.appburbujas.Class.Lavadora

class DbManager(context: Context?) {
    //lamamos la conexion

    var dbHelper = BdHelper(context)
    var db : SQLiteDatabase?= null

    //metodo para abrir la bd en modo escritura
    fun openDb(){
        db = dbHelper.writableDatabase
    }

    //metodo para abrir la bd en modo lectura
    fun openDbReadable(){
        db = dbHelper.readableDatabase
    }

    //metodo para cerrar la base de datos
    fun closeDb(){
        if(db!=null){
            db?.close()
        }
    }

    fun insertData(lavadora: Lavadora): Long{
        openDb()
        var contentValues = ContentValues()
        contentValues.put(Constantes.COLUM_MARCA,lavadora.marca)
        contentValues.put(Constantes.COLUM_CAP,lavadora.capacidad)
        contentValues.put(Constantes.COLUM_ESTADO,"libre")
        contentValues.put(Constantes.COLUM_VALOR_HORA,lavadora.valorHora)
        contentValues.put(Constantes.COLUMN_IMAGEN,lavadora.imagen)
        //llamamos el metodo insert de SQLIteDatabase
        var result = db?.insert(Constantes.NAME_TABLE,null,contentValues)
        return result!!

    }

    fun getData():Cursor{
        openDb()
        return db!!.rawQuery(Constantes.CONSULT,null)
    }

    //metodo para consultar todos los datos de la bd

    fun getAllData(): Cursor {
        openDbReadable()
        return db!!.query(Constantes.NAME_TABLE, arrayOf(Constantes.COLUM_ID,Constantes.COLUM_MARCA,
            Constantes.COLUM_CAP,Constantes.COLUM_ESTADO,Constantes.COLUM_VALOR_HORA,Constantes.COLUMN_IMAGEN),
            null,null,null,null,null)

    }

    fun getOneData(id:String):Cursor{
        openDb()
        return db!!.rawQuery("SELECT id FROM "+Constantes.NAME_TABLE+" WHERE id=?", arrayOf(id))
    }

    fun deleteData(id:String){
        openDb()
        db?.delete(Constantes.NAME_TABLE,"id =?",arrayOf(id))



    }


}