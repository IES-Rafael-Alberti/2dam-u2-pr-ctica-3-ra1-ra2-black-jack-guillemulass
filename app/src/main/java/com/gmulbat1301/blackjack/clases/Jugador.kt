package com.gmulbat1301.blackjack.clases

class Jugador(var nombre: String, var fichas: Int, var mano: MutableList<Carta>) {
    fun pideCarta(carta: Carta){
        this.mano.add(carta)
    }
    fun apuesta(num: Int){
        this.fichas -= num
    }
    fun ganaApuesta(num: Int){
        this.fichas += num
    }
}