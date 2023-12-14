package com.gmulbat1301.blackjack.clases

class Carta(nombre: Naipes, palo: Palos,idDrawable: Int) {

    var puntosMin: Int
    var puntosMax: Int
    var IdDrawable = idDrawable


    init {

        if (nombre.valor == 1) {
            puntosMax = 11
            puntosMin = 1
        } else {
            puntosMax = nombre.valor
            puntosMin = nombre.valor
        }

    }
}