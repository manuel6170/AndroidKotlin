package com.mcmoviles.manuel.appburbujas.Class

data class Lavadora(
    var id: String,
    var marca: String,
    var capacidad: String,
    var valorHora: Int,
    var estado: String,
    var imagen: String
){
    constructor():this("","","",1000,"",""){}
}