package com.gmulbat1301.blackjack.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmulbat1301.blackjack.clases.Baraja
import com.gmulbat1301.blackjack.clases.Carta
import com.gmulbat1301.blackjack.clases.Jugador

/**
 * @author: Guillermo Mulas
 * Clase que controla el modo multijugador (1v1)
 * @property baraja -> Objeto Baraja unico del que se sacan las cartas
 * @property _handplayer1 -> Lista de Carta  privada que representa la mano del jugador 1 de forma privada
 * @property handplayer1 -> _handplayer1 para ser accedido desde pvpScreenFun
 * @property _handplayer2 -> Lista de Carta  privada que representa la mano del jugador 2 de forma privada
 * @property handplayer2 -> _handplayer2 para ser accedido desde pvpScreenFun
 * @property _playerTurn -> Int que representa el turno del jugador actual, toma el valor 1 o 2 respectivamente
 * @property playerTurn -> _playerTurn para ser accedido desde pvpScreenFun
 * @property _player1Turn -> Boolean que representa si es el turno actual es del jugador 1
 * @property player1Turn -> _player1Turn para ser accedido desde pvpScreenFun
 * @property _player2Turn -> Boolean que representa si es el turno actual es del jugador 2
 * @property player2Turn -> _player2Turn para ser accedido desde pvpScreenFun
 * @property _player1Skipped -> Boolena que representa si el jugador 1 ha saltado su turno
 * @property _player2Skipped -> Boolena que representa si el jugador 2 ha saltado su turno
 * @property _player1Finished -> Boolean que representa si es el jugador 1 ha terminado de jugar
 * @property player1Finished -> _player1Finished para ser accedido desde pvpScreenFun
 * @property _player2Finished -> Boolean que representa si es el jugador 2 ha terminado de jugar
 * @property player2Finished -> _player2Finished para ser accedido desde pvpScreenFun
 * @property _refreshPlayerCards -> Boolean que se usa para refrescar la LazyRow y que se muestren las nuevas cartas añadidas
 * @property refreshPlayerCards -> _refreshPlayerCards para ser accedido desde pvpScreenFun
 * @property _finishGame -> Boolean que representa si se ha terminado la partida
 * @property finishGame -> _finishGame para ser accedido desde pvpScreenFun
 */
class PVEViewModel(application: Application) : AndroidViewModel(application){

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
        /**
         * Se da valor a todas las variables, porque al ser nulas no se puede llamar a la clase PVPViewModel
         */
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

    /**
     * Obtiene el valor total de la mano de un jugador
     *
     * @param playerID Id del jugador que del que se quiere obtener el valor de la mano
     */
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

    /**
     * Añade una carta a la mano de un jugador
     *
     * @param playerID Id del jugador al que se quiere dar la carta
     */
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

    /**
     * Controla el cambio de turno de un jugador a otro
     *
     * @param playerID Id del jugador que pasa su turno
     */
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

    /**
     * Controla _playerFinished, es decir, si el jugador ha terminado de jugar esta partida
     * Controla _finishGame, si ambos jugadores han terminado de jugar acaba la partida
     *
     * @param playerID Id del jugador del que se controla
     */
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

    /**
     * Funcion que sirve para refrescar la LazyRow y mostrar las nuevas cartas añadidas
     */
    private fun refreshCards(){
        _refreshPlayerCards.value = !_refreshPlayerCards.value!!
    }

    /**
     * Añade dos cartas a la mano de cada jugador al principio de cada partida
     */
    private fun initialHandFiller(){
        giveCard(1)
        giveCard(2)
        giveCard(1)
        giveCard(2)
    }

    /**
     * Reinicia las propiedades para empeczar un nuevo juego
     */
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

    /**
     * Devuelve una cadena que se muestra en la pantalla en la que se indica los puntos de cada jugador
     */
    fun playersPoints():String{
        return "Jugador 1 : ${handValue(1)} puntos\n\n Jugador 2 : ${handValue(2)} puntos\n"
    }

    /**
     * Muestra el resultado de la partida, quien es el ganador, si hay empate o si ambos pierden,
     * dependiendo de el valor de la mano de cada jugador
     */
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