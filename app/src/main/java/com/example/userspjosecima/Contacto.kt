package com.example.userspjosecima

class Contacto(nombre:String, apellidos:String, empresa:String, edad:Int, peso:Float, telefono:String, email:String,direccion:String,  foto:Int) {

    var nombre:String = ""
    var apellidos:String = ""
    var empresa:String = ""
    var edad:Int = 0
    var peso:Float = 0.0F
    var telefono:String = ""
    var email:String = ""
    var direccion:String =""
    var foto:Int = 0

    init{
        this.nombre = nombre
        this.apellidos  = apellidos
        this.empresa = empresa
        this.edad = edad
        this.peso = peso
        this.telefono = telefono
        this.email = email
        this.direccion = direccion
        this.foto = foto
    }


}