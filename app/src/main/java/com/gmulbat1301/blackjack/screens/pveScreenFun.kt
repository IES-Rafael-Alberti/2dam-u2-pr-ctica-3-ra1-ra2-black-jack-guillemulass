package com.gmulbat1301.blackjack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.gmulbat1301.blackjack.routes.Routes


/**
 * Funcion Padre de la pantalla, controla que se muestra,
 * si la pantalla de juego o la de final de la partida
 *
 * @param navController Controlador de navegacion
 * @param pveViewModel Clase controladora
 */
@Composable
fun screenPVE(
    navController: NavHostController,
    pveViewModel: PVEViewModel
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        PVEVisual(
            pveViewModel,
            navController,
            )



    }
}

@Composable
fun PVEVisual(
    pveViewModel: PVEViewModel,
    navController: NavHostController
){

    val playerTurn: Int by pveViewModel.playerTurn.observeAsState(initial = 1)
    val player1Turn: Boolean by pveViewModel.player1Turn.observeAsState(initial = true)
    val player1Finished: Boolean by pveViewModel.player1Finished.observeAsState(initial = false)
    val player2Turn: Boolean by pveViewModel.player2Turn.observeAsState(initial = false)
    val player2Finished: Boolean by pveViewModel.player2Finished.observeAsState(initial = false)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopRowPvE(navController)

        PVEPlayerButtons(
            pveViewModel,
            1,
            player1Turn,
            playerTurn,
            player1Finished
        )

    }
}

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
fun PVEPlayerButtons(
    pveViewModel: PVEViewModel,
    playerID: Int,
    player1Turn: Boolean,
    playerTurn: Any?,
    player1Finished: Boolean
) {



}