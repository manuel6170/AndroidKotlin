package com.mcmoviles.manuel.appburbujas.Model

class Constantes {
    companion object {

        val NAME_BD ="BDLavadoras"
        val VERSION_BD= 5
        val NAME_TABLE="lavadoras"
        val COLUM_ID="id"
        val COLUM_MARCA="marca"
        val COLUM_CAP="capacidad"
        val COLUM_VALOR_HORA="valor_hora"
        val COLUM_ESTADO="estado"
        val COLUMN_IMAGEN="imagen"
        val CONSULT= "select * from $NAME_TABLE"

        val COLUMN_NOMBRE="nombre"
        val COLUMN_TELEFONO="telefono"
        val COLUMN_IDENTIFICACION="identificacion"
        val NAME_TABLE_USUARIO="usuarios"

        val table="create table $NAME_TABLE (" +
                "$COLUM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUM_MARCA TEXT," +
                "$COLUM_CAP TEXT," +
                "$COLUM_VALOR_HORA INTEGER," +
                "$COLUM_ESTADO TEXT," +
                "$COLUMN_IMAGEN TEXT )"

        val tableUsuarios="create table $NAME_TABLE_USUARIO (" +
                "$COLUM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOMBRE TEXT," +
                "$COLUMN_TELEFONO TEXT," +
                "$COLUMN_IDENTIFICACION TEXT)"
    }


}

