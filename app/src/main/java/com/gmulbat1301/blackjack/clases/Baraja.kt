package com.gmulbat1301.blackjack.clases

class Baraja {

    companion object {
        val listaCartas: ArrayList<Carta> = arrayListOf()
        init {
            creaBaraja()
            barajar()
        }

        fun creaBaraja(){
            listaCartas.clear()
            for(i in Palos.values()){
                for(x in Naipes.values()){
                    val cartaAdd = Carta(x,i)
                    listaCartas.add(cartaAdd)
                }
            }
        }

        fun barajar(){
            listaCartas.shuffle()
        }

        fun cogerCarta():Carta{
            if (listaCartas.isNotEmpty()){

                return Carta(Naipes.AS,Palos.PICAS)
            }
            return Carta(Naipes.AS,Palos.PICAS)
        }

    }



}