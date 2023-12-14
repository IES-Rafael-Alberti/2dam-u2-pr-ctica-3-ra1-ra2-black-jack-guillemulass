package com.gmulbat1301.blackjack.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmulbat1301.blackjack.clases.Baraja
import com.gmulbat1301.blackjack.clases.Carta
import com.gmulbat1301.blackjack.clases.Jugador

class PVPViewModel(application: Application) : AndroidViewModel(application){

    private val baraja = Baraja

    private val _player1 = MutableLiveData<Jugador>()
    val player1 : LiveData<Jugador> = _player1

    private val _player2 = MutableLiveData<Jugador>()
    val player2 : LiveData<Jugador> = _player2

    private val _handplayer1 = MutableLiveData<MutableList<Carta>>()
    val handplayer1 : LiveData<MutableList<Carta>> = _handplayer1

    private val _handplayer2 = MutableLiveData<MutableList<Carta>>()
    val handplayer2 : LiveData<MutableList<Carta>> = _handplayer2

    private val _playerTurn = MutableLiveData<Int>()
    val playerTurn : LiveData<Int> = _playerTurn

    private val _player1Turn = MutableLiveData<Boolean>()
    val player1Turn: LiveData<Boolean> = _player1Turn

    private val _player2Turn = MutableLiveData<Boolean>()
    val player2Turn: LiveData<Boolean> = _player2Turn

    private val _player1Skipped = MutableLiveData<Boolean>()
    private val _player2Skipped = MutableLiveData<Boolean>()

    private val _player1Finished = MutableLiveData<Boolean>()
    val player1Finished: LiveData<Boolean> = _player1Finished

    private val _player2Finished = MutableLiveData<Boolean>()
    val player2Finished: LiveData<Boolean> = _player2Finished

    private val _refreshPlayerCards = MutableLiveData<Boolean>()
    val refreshPlayerCards : LiveData<Boolean> = _refreshPlayerCards

    private val _finishGame = MutableLiveData<Boolean>()
    val finishGame: LiveData<Boolean> = _finishGame


    init {
        baraja.crearBaraja()
        _playerTurn.value = 1

        _handplayer1.value = mutableListOf()
        _handplayer2.value = mutableListOf()
        _player1Turn.value = true
        _player2Turn.value = false
        _player1Skipped.value = false
        _player2Skipped.value = false
        _refreshPlayerCards.value = false
        _finishGame.value = false
        _player1Finished.value = false
        _player2Finished.value = false
        initialHandFiller()
    }

    fun handValue(playerID: Int): Int {
        var value = 0
        if (playerID == 1){
            for (card in _handplayer1.value!!){
                value += card.puntosMin
            }
        } else{
            for (card in _handplayer2.value!!){
                value += card.puntosMin
            }
        }
        return value
    }

    fun giveCard(playerID: Int){
        val newCard = baraja.cogerCarta()
        if (playerID == 1) {
            handplayer1.value!!.add(newCard)
        }
        else {
            handplayer2.value!!.add(newCard)
        }
        refreshCards()
        endTurn(playerID)
    }

    fun skipTurn(playerID: Int){
        if (playerID == 1){
            _player1Skipped.value = true
            _player1Turn.value = false
            _player2Turn.value = true
            _playerTurn.value = 2
            if (handValue(1)>handValue(2)){
                _player2Finished.value = false
                _player2Skipped.value = false
            }
        }else{
            _player2Skipped.value = true
            _player1Turn.value = true
            _player2Turn.value = false
            _playerTurn.value = 1
            if (handValue(1)<handValue(2)){
                _player1Finished.value = false
                _player1Skipped.value = false
            }
        }
        endTurn(playerID)
    }

    private fun endTurn(playerID: Int){
        if ((_player1Skipped.value == true && playerID == 1) || (handValue(1)>=21)) {
            _player1Finished.value = true
        }
        if ((_player2Skipped.value == true && playerID ==2) || (handValue(2)>=21)){
            _player2Finished.value = true
        }

        if (_player1Finished.value == true && _player2Finished.value == true){
            _finishGame.value = true
        }
    }

    private fun refreshCards(){
        _refreshPlayerCards.value = !_refreshPlayerCards.value!!
    }

    private fun initialHandFiller(){
        giveCard(1)
        giveCard(1)
        giveCard(2)
        giveCard(2)
    }

    fun restartGame(){

        baraja.crearBaraja()
        _playerTurn.value = 1
        _player1Turn.value = true
        _player2Turn.value = false
        _refreshPlayerCards.value = false
        _finishGame.value = false
        _handplayer1.value?.clear()
        _handplayer2.value?.clear()
        _player1Skipped.value = false
        _player2Skipped.value = false
        _player1Finished.value = false
        _player2Finished.value = false
        initialHandFiller()

    }

    fun playersPoints():String{
        return "Jugador 1 : ${handValue(1)} puntos\n\n Jugador 2 : ${handValue(2)} puntos\n"
    }

    fun winnerText(): String {
        val handValue1 = handValue(1)
        val handValue2 = handValue(2)

        return when {
            (handValue1 in 1 until 22 && (handValue1 > handValue2 || handValue2 > 21)) ->
                "Gana el Jugador 1"
            (handValue2 in 1 until 22 && (handValue2 > handValue1 || handValue1 > 21)) ->
                "Gana el Jugador 2"
            (handValue1 == handValue2 && handValue1 in 1 until 22) ->
                "Empate"
            else ->
                "Ambos jugadores pierden"
        }
    }

}