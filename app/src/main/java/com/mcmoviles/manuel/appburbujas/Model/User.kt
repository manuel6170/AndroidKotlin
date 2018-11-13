package com.mcmoviles.manuel.appburbujas.Model



import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User {
    var name: String? = null
    var email: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(username: String?, email: String?) {
        this.name = username
        this.email = email
    }
}
/*
class User {

    var id: Int = 0
    var nombre : String = ""
    var edad : Int = 0

    constructor(nombre:String, edad:Int) {
        this.nombre= nombre
        this.edad = edad

    }

}*/