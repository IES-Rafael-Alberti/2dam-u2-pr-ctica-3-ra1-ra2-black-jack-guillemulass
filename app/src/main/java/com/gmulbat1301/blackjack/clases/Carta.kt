package com.gmulbat1301.blackjack.clases

import android.graphics.drawable.AdaptiveIconDrawable

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
        /*
        idDrawable = if (nombre.valor !=10){
            nombre.valor + (palo.valor * 13)
        } else{
            when (nombre.name) {
                "VALET" -> {
                    nombre.valor + 1 + (palo.valor * 13)
                }
                "DAME" -> {
                    nombre.valor + 2 + (palo.valor * 13)
                }
                else -> {
                    nombre.valor + 3 + (palo.valor * 13)
                }
            }
        }
         */
    }
}