package com.gmulbat1301.blackjack.clases

import com.gmulbat1301.blackjack.R

class Baraja {

    companion object {
        private val listaCartas: ArrayList<Carta> = arrayListOf()

        fun crearBaraja(){
            var cont = 0
            listaCartas.clear()
            for(i in Palos.values()){
                for(x in Naipes.values()){
                    val cartaAdd = Carta(x,i, drawableIdList[cont])
                    cont++
                    listaCartas.add(cartaAdd)
                }
            }
            barajar()
        }

        private fun barajar(){
            listaCartas.shuffle()
        }

        fun cogerCarta():Carta{
            //return if (listaCartas.isNotEmpty()){
                return listaCartas.removeAt(listaCartas.size - 1)
            //}else Carta(Naipes.ROI,Palos.PICAS,0)
        }

        private val drawableIdList = mutableListOf(
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4,
            R.drawable.c5,
            R.drawable.c6,
            R.drawable.c7,
            R.drawable.c8,
            R.drawable.c9,
            R.drawable.c10,
            R.drawable.c11,
            R.drawable.c12,
            R.drawable.c13,
            R.drawable.c14,
            R.drawable.c15,
            R.drawable.c16,
            R.drawable.c17,
            R.drawable.c18,
            R.drawable.c19,
            R.drawable.c20,
            R.drawable.c21,
            R.drawable.c22,
            R.drawable.c23,
            R.drawable.c24,
            R.drawable.c25,
            R.drawable.c26,
            R.drawable.c27,
            R.drawable.c28,
            R.drawable.c29,
            R.drawable.c30,
            R.drawable.c31,
            R.drawable.c32,
            R.drawable.c33,
            R.drawable.c34,
            R.drawable.c35,
            R.drawable.c36,
            R.drawable.c37,
            R.drawable.c38,
            R.drawable.c39,
            R.drawable.c40,
            R.drawable.c41,
            R.drawable.c42,
            R.drawable.c43,
            R.drawable.c44,
            R.drawable.c45,
            R.drawable.c46,
            R.drawable.c47,
            R.drawable.c48,
            R.drawable.c49,
            R.drawable.c50,
            R.drawable.c51,
            R.drawable.c52
        )
    }

}