package com.mcmoviles.manuel.appburbujas.Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BdHelper(context: Context?) :

    SQLiteOpenHelper(context, Constantes.NAME_BD, null,Constantes.VERSION_BD) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constantes.table)
        db?.execSQL(Constantes.tableUsuarios)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db?.execSQL("DROP TABLE IF EXISTS ${Constantes.NAME_TABLE}")
        db?.execSQL("DROP TABLE IF EXISTS ${Constantes.NAME_TABLE_USUARIO}")
        onCreate(db)

    }
}