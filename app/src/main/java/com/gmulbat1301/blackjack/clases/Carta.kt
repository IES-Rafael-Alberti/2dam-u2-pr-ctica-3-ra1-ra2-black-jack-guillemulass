package com.gmulbat1301.blackjack.clases

class Carta(nombre: Naipes, palo: Palos) {
    protected var puntosMin: Int
    protected var puntosMax: Int
    protected var idDrawable: Int

    init {
        if (nombre.valor == 1) {
            puntosMax = 11
            puntosMin = 1
        } else {
            puntosMax = nombre.valor
            puntosMin = nombre.valor
        }
        idDrawable = nombre.valor + (palo.valor * 13)
        // las cartas tienen como nombre c1 hasta c52, añadir c mas tarde
    }
}