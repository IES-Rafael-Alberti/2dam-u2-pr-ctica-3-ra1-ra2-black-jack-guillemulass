package com.gmulbat1301.blackjack.userinterface

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmulbat1301.blackjack.clases.Baraja
import com.gmulbat1301.blackjack.clases.Carta

/**
 * @author: Guillermo Mulas
 * Clase que controla el modo Carta Alta
 * @property baraja -> Objeto Baraja unico del que se sacan las cartas
 * @property _showReverseCard -> Boolean que se activo si no se ha sacado carta o si la baraja esta vacia, para que se muestre el reverso de una carta en vez de una carta
 * @property showReverseCard -> showReverseCard publico para ser accedido desde HighestCardScreen
 * @property _card -> Ultima Carta sacada de la baraja
 * @property card -> _card publico para ser accedido desde HighestCardScreen
 * @property _cardCounter -> Int que cuenta las cartas que quedan en la baraja, empieza en 52 y se le resta 1 cada vez que se saca una carta
 * @property cardCounter -> _cardCounter publico para ser accedido desde HighestCardScreen
 */
class HighestCardViewModel(application: Application) : AndroidViewModel(application) {

    private val baraja = Baraja

    private val _showReverseCard = MutableLiveData<Boolean>()
    val showReverseCard: LiveData<Boolean> = _showReverseCard

    private val _card = MutableLiveData<Carta>()
    val card: LiveData<Carta> = _card

    private val _cardCounter = MutableLiveData<Int>()
    val cardCounter: LiveData<Int> = _cardCounter

    init {
        baraja.crearBaraja()
        _cardCounter.value = 52
    }

    fun getCard(){
        _cardCounter.value = _cardCounter.value!! - 1
        if (baraja.tamanoBaraja()<1){
            _showReverseCard.value = true
        } else{
            _showReverseCard.value = false
            _card.value = baraja.cogerCarta()
        }
    }

    fun restartDeck(){
        _showReverseCard.value = true
        _cardCounter.value = 52
        baraja.crearBaraja()
    }

}