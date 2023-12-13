package com.gmulbat1301.blackjack.clases

class Jugador(var nombre: String, var fichas: Int, var mano: MutableList<Carta>) {
    fun pideCarta(carta: Carta){
        this.mano.add(carta)
    }
    fun apuesta(apuesta: Int){
        this.fichas -= apuesta
    }
    fun gana(apuesta: Int){
        this.fichas += apuesta
    }

    constructor() : this("",0, mutableListOf<Carta>())

}