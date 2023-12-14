package com.gmulbat1301.blackjack.screens

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

@SuppressLint("MutableCollectionMutableState", "SuspiciousIndentation")
@Composable
fun ScreenPVP(
    navController: NavHostController,
    pvpViewModel: PVPViewModel
) {

    val finishGame: Boolean by pvpViewModel.finishGame.observeAsState(initial = false)
    val standPlayer1: Boolean by pvpViewModel.player1Turn.observeAsState(initial = true)
    val standPlayer2: Boolean by pvpViewModel.player2Turn.observeAsState(initial = false)
    val playerTurn: Int by pvpViewModel.playerTurn.observeAsState(initial = 1)

    if (!finishGame){
        PVPVisual(
            pvpViewModel,
            navController,
            standPlayer1,
            standPlayer2,
            playerTurn
        )
    } else{
        PVPEndVisual(
            pvpViewModel,
            navController
        )
    }
}


@Composable
fun PVPVisual(
    pvpViewModel: PVPViewModel,
    navController: NavHostController,
    standPlayer1: Boolean,
    standPlayer2: Boolean,
    playerTurn: Int
){
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
        TopRow(navController)
        PlayerButtons(
            pvpViewModel,
            1,
            standPlayer1,
            playerTurn,
            player1Finished
        )

        PlayerButtons(
            pvpViewModel,
            2,
            standPlayer2,
            playerTurn,
            player2Finished
        )
    }
}


@Composable
fun TopRow(navController: NavHostController) {
    Text(
        text = "Multijugador",
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Button(
        shape = RectangleShape,
        onClick = { navController.navigate(Routes.mainScreen.route) },
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

@Composable
fun PlayerButtons(
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
    PlayerCards(
        if (playerID == 1) handplayer1
        else handplayer2,
        refreshplayerCards
    )

}

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

@Composable
fun CardPrinter(card: Carta) {
    Image(
        modifier = Modifier
            .height(200.dp),
        painter = painterResource(id = card.IdDrawable),
        contentDescription = null
    )
}

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
        TopRow(navController)

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

        if (winnerText == "Gana el Jugador 1" || winnerText == "Gana el Jugador 2" ){
            Text(
                text = "Mano Ganadora: ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
         PlayerCards(if (winnerText == "Gana el Jugador 1") winnerHand1 else winnerHand2,
             refreshWinnerCards
         )
        }
    }


}

