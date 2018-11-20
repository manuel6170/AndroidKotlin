package com.mcmoviles.manuel.appburbujas.Class

data class Reserva(
    var idReserva:String,
    var idLavadora: String,
    var idUsuario: String,
    var totalPagar: Int,
    var horasUso: Int,
    var direccion: String,
    var estado: String,
    var horaEnvio:String) {

    constructor():this("","","",0,0,"","",""){}

}