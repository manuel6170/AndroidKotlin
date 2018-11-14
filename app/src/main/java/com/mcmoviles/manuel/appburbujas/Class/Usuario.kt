package com.mcmoviles.manuel.appburbujas.Class

data class Usuario(

    var nombre: String,
    var telefono: String,
    var identificacion: String
){
    constructor():this("","",""){}
}