package com.mcmoviles.manuel.appburbujas.Class

data class Usuario(
   val nombre: String,
   var telefono: String,
   var identificacion: String,
   var direccion:String
){
    constructor():this("","","",""){}

}