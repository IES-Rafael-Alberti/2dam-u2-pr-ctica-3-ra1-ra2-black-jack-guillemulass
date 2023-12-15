package com.gmulbat1301.blackjack.userinterface

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gmulbat1301.blackjack.R
import com.gmulbat1301.blackjack.clases.Carta
import com.gmulbat1301.blackjack.routes.Routes


/**
 * Funcion Padre de la pantalla, controla que se muestra,
 * si la pantalla de juego o la de final de la partida
 *
 * @param navController Controlador de navegacion
 * @param pvpViewModel Clase controladora
 */
@SuppressLint("MutableCollectionMutableState", "SuspiciousIndentation")
@Composable
fun ScreenPVP(
    navController: NavHostController,
    pvpViewModel: PVPViewModel
) {

    val finishGame: Boolean by pvpViewModel.finishGame.observeAsState(initial = false)
    val playerTurn: Int by pvpViewModel.playerTurn.observeAsState(initial = 1)

    /**
     * Cuando la la partida no ha terminado (finishGame) se muestran los botones para jugar,
     * cuando acaba se muestra el resultado y da la opcion de reiniciar la partida
     */
    if (!finishGame){
        PVPVisual(
            pvpViewModel,
            navController,
            playerTurn
        )
    } else{
        PVPEndVisual(
            pvpViewModel,
            navController
        )
    }
}


/**
 * Funcion principal de la partida, llama al resto de funciones y controla las variables
 *
 * @param pvpViewModel Clase controladora
 * @param playerTurn Int que muestra de que jugador es turno actual (1 o 2)
 */
@Composable
fun PVPVisual(
    pvpViewModel: PVPViewModel,
    navController: NavHostController,
    playerTurn: Int
){

    val player1Turn: Boolean by pvpViewModel.player1Turn.observeAsState(initial = true)
    val player2Turn: Boolean by pvpViewModel.player2Turn.observeAsState(initial = false)
    val player1Finished: Boolean by pvpViewModel.player1Finished.observeAsState(initial = false)
    val player2Finished: Boolean by pvpViewModel.player2Finished.observeAsState(initial = false)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopRowPvP(navController)

        PVPPlayerButtons(
            pvpViewModel,
            1,
            player1Turn,
            playerTurn,
            player1Finished
        )

        PVPPlayerButtons(
            pvpViewModel,
            2,
            player2Turn,
            playerTurn,
            player2Finished
        )
    }
}


/**
 * Parte superior de la pantalla, se muestra el modo de juego y un boton para volver a la pantalla inicial
 */
@Composable
fun TopRowPvP(navController: NavHostController) {
    Text(
        text = "Multijugador",
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Button(
        shape = RectangleShape,
        onClick = { navController.navigate(Routes.MainScreen.route) },
        colors = ButtonDefaults.buttonColors(Color.Red)
    ) {
        Text(
            text = "Pantalla Inicial",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
    Spacer(dp = 10)
}


/**
 * Funcion unica que controla y crea los botones para ambos jugadores dependiendo del Id introducido y
 * llama a la funcion para mostrar sus cartas
 *
 * @param pvpViewModel Clase controladora
 * @param playerID Id del jugador del que se va mostrar los botones y la mano
 * @param actualPlayerTurn Boolean que mira si es el turno del jugador actual (true) o no (false)
 * @param playerTurn Int que muestra de que jugador es turno actual (1 o 2)
 * @param playerFinished Boolean que controla si el jugador ha terminado o no
 */
@Composable
fun PVPPlayerButtons(
    pvpViewModel: PVPViewModel,
    playerID: Int,
    actualPlayerTurn: Boolean,
    playerTurn: Int,
    playerFinished: Boolean
){

    val handplayer1: List<Carta> by pvpViewModel.handplayer1.observeAsState(listOf())
    val handplayer2: List<Carta> by pvpViewModel.handplayer2.observeAsState(listOf())
    val refreshplayerCards: Boolean by pvpViewModel.refreshPlayerCards.observeAsState(initial = false)


    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            enabled = !playerFinished && actualPlayerTurn && playerTurn == playerID,
            onClick = {
                pvpViewModel.giveCard(playerID)
            }
        ) {
            Text(
                text = "Pedir Carta",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(dp = 20)

        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            enabled = actualPlayerTurn && playerTurn == playerID,
            onClick = {
                pvpViewModel.skipTurn(playerID)
            }
        ) {
            Text(
                text = "Pasar Turno",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Jugador $playerID",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Puntos : ${pvpViewModel.handValue(playerID)}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
    Spacer(dp = 5)
    /**
     * Muestra las cartas del jugador actual,
     * se controla que mano se muestra con el Id del jugador
     */
    PlayerCards(
        if (playerID == 1) handplayer1
        else handplayer2,
        refreshplayerCards
    )

}


/**
 * Contiene la LazyRow en la que se muestran las manos de los jugadores
 *
 * @param handplayer es la mano que va a mostrarse
 * @param refresh solo se pide, para refrescar la LazyRow
 */
@Composable
fun PlayerCards(
    handplayer: List<Carta>,
    refresh: Boolean
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy((-80).dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(10.dp),
    ) {
        //Cartas
        items(handplayer) { carta ->
            CardPrinter(carta)
        }
    }
    Spacer(dp = 5)
}


/**
 * Muestra las imagenes de las cartas
 */
@Composable
fun CardPrinter(card: Carta) {
    Image(
        modifier = Modifier
            .height(200.dp),
        painter = painterResource(id = card.IdDrawable),
        contentDescription = null
    )
}

/**
 * Funcion que muestra la pantalla de final de la partida,
 * da la opcion de reiniciar la partida, muestra el resultado y la mano ganadora
 *
 * @param pvpViewModel Clase controladora
 */
@Composable
fun PVPEndVisual(
    pvpViewModel: PVPViewModel,
    navController: NavHostController
){

    val winnerHand1: List<Carta> by pvpViewModel.handplayer1.observeAsState(listOf())
    val winnerHand2: List<Carta> by pvpViewModel.handplayer2.observeAsState(listOf())
    val refreshWinnerCards: Boolean by pvpViewModel.refreshPlayerCards.observeAsState(initial = false)
    val  winnerText = pvpViewModel.winnerText()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopRowPvP(navController)

        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                pvpViewModel.restartGame()
            }
        ) {
            Text(
                text = "Reiniciar Juego",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(dp = 20)

        Text(
            text = pvpViewModel.playersPoints(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = winnerText,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(dp = 5)

        // Si el winnerText contiene un ganador y no un empate o una doble derrota muestra la mano ganadora
        if (winnerText == "Gana el Jugador 1" || winnerText == "Gana el Jugador 2" ){
            Text(
                text = "Mano Ganadora: ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            // Muestra la mano ganadora dependiendo del winnerText
         PlayerCards(if (winnerText == "Gana el Jugador 1") winnerHand1 else winnerHand2,
             refreshWinnerCards
         )
        }
    }

}

