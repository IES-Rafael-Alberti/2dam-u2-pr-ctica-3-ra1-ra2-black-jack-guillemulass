package com.gmulbat1301.blackjack.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
 * @param pveViewModel Clase controladora
 */
@Composable
fun ScreenPVE(
    navController: NavHostController,
    pveViewModel: PVEViewModel
){
    val finishGame: Boolean by pveViewModel.finishGame.observeAsState(initial = false)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (!finishGame){
            PVEVisual(
                pveViewModel,
                navController
            )
        } else {
            PVEEndVisual(
                pveViewModel,
                navController
            )
        }



    }
}


/**
 * Interfaz completa de la pantalla miestras se esta jugando,
 * llama a las funciones del encabezado, interfaz de Jugador e interfaz de la IA
 *
 * @param navController Controlador de navegacion
 * @param pveViewModel Clase controladora
 */
@Composable
fun PVEVisual(
    pveViewModel: PVEViewModel,
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopRowPvE(navController)

        PVEPlayerButtons(pveViewModel)

        IAInterface(pveViewModel)

    }
}


/**
 * Parte superior de la pantalla, muestra el modo de juego y un boton para volver a la pantalla inicial
 *
 * @param navController Controlador de navegacion
 */
@Composable
fun TopRowPvE(navController: NavHostController){

    Text(
        text = "Individual",
        fontSize = 75.sp,
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
 * Funcion que controla la interfaz del jugador, contiene los botones
 * y llama a la funcion para mostrar sus cartas,
 * la cual está en pvpScreenFun
 *
 * @param pveViewModel Clase controladora
 */
@Composable
fun PVEPlayerButtons(
    pveViewModel: PVEViewModel
) {

    val playerTurn: Int by pveViewModel.playerTurn.observeAsState(initial = 1)
    val actualPlayerTurn: Boolean by pveViewModel.player1Turn.observeAsState(initial = true)
    val playerFinished: Boolean by pveViewModel.player1Finished.observeAsState(initial = false)
    val handplayer1: List<Carta> by pveViewModel.handplayer1.observeAsState(listOf())
    val refreshCards: Boolean by pveViewModel.refreshPlayerCards.observeAsState(initial = false)
    val playerID = 1
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        pveViewModel.controlerIA()

        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            enabled = !playerFinished && actualPlayerTurn && playerTurn == playerID,
            onClick = {
                pveViewModel.giveCard(playerID)
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
                pveViewModel.skipTurn(playerID)
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
            text = "Jugador",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Puntos : ${pveViewModel.handValue(playerID)}",
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
        handplayer1,
        refreshCards
    )

}


/**
 * Funcion que controla la interfaz de la IA,
 * llama a la funcion para mostrar sus cartas,
 * la cual está en pvpScreenFun
 *
 * @param pveViewModel Clase controladora
 */
@Composable
fun IAInterface(
    pveViewModel: PVEViewModel
){

    val handplayer2: List<Carta> by pveViewModel.handplayer2.observeAsState(listOf())
    val refreshCardsIA: Boolean by pveViewModel.refreshPlayerCards.observeAsState(initial = false)
    val playerID = 2


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Crupier",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Puntos : ${pveViewModel.handValue(playerID)}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }

    PlayerCards(
        handplayer2,
        refreshCardsIA
    )

}


/**
 * Funcion que muestra la pantalla final de la partida,
 * da la opcion de reiniciar la partida, muestra el resultado y la mano ganadora
 *
 * @param navController Controlador de navegacion
 * @param pveViewModel Clase controladora
 */
@Composable
fun PVEEndVisual(
    pveViewModel: PVEViewModel,
    navController: NavHostController
){

    val winnerHand1: List<Carta> by pveViewModel.handplayer1.observeAsState(listOf())
    val winnerHand2: List<Carta> by pveViewModel.handplayer2.observeAsState(listOf())
    val refreshWinnerCards: Boolean by pveViewModel.refreshPlayerCards.observeAsState(initial = false)
    val winnerText = pveViewModel.winnerText()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopRowPvE(navController)

        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                pveViewModel.restartGame()
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
            text = pveViewModel.playersPoints(),
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
        if (winnerText == "Has Ganado!" || winnerText == "Gana la Casa"){
            Text(
                text = "Mano Ganadora: ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            // Muestra la mano ganadora dependiendo del winnerText
            PlayerCards(if (winnerText == "Has Ganado!") winnerHand1 else winnerHand2,
                refreshWinnerCards
            )
        }
    }

}