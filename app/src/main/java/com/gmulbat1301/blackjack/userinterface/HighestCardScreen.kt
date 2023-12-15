package com.gmulbat1301.blackjack.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import com.gmulbat1301.blackjack.R
import com.gmulbat1301.blackjack.clases.Carta
import com.gmulbat1301.blackjack.clases.Naipes
import com.gmulbat1301.blackjack.clases.Palos
import com.gmulbat1301.blackjack.routes.Routes

/**
 * Funcion principal que llama a las demas
 *
 * @param navController Controlador de navegacion
 * @param highestCardViewModel Clase controladora
 */
@Composable
fun HighestCardScreen(
    navController: NavController,
    highestCardViewModel: HighestCardViewModel
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){ 
        HCTopRow(navController)
        
        Spacer(dp = 20)
        
        HCMainBody(highestCardViewModel)

    }
}

/**
 * Encabezado de la pantalla, contiene el nombre del modo de juego y un boton a la pantalla principal
 *
 * @param navController Controlador de navegacion
 */
@Composable
fun HCTopRow(
    navController: NavController
){
    Text(
        text = "Highest Card",
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
 * Funcion que contiene la interfaz del juego, llama y controla a los botones y a la fucion que muestra la carta
 *
 * @param highestCardViewModel Clase controladora
 */
@Composable
fun HCMainBody(
    highestCardViewModel: HighestCardViewModel
){

    val showReverseCard: Boolean by highestCardViewModel.showReverseCard.observeAsState(initial = true)
    val card: Carta by highestCardViewModel.card.observeAsState(Carta(Naipes.AS,Palos.PICAS,R.drawable.creverso))
    val cardCounter: Int by highestCardViewModel.cardCounter.observeAsState(initial = 52)


    HCCardPrinter(
        card,
        showReverseCard
    )

    Spacer(dp = 10)

    Text(
        text = "Quedan $cardCounter cartas en la baraja",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Spacer(dp = 10)

    Row (
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ){

        Button(
            shape = RectangleShape,
            enabled = cardCounter>=1,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                highestCardViewModel.getCard()
            }
        ) {
            Text(
                text = "Pedir Carta",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(dp = 5)

        Button(
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                highestCardViewModel.restartDeck()
            }
        ) {
            Text(
                text = "Reiniciar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

}

/**
 * Funcion que muestra la ultima carta sacada de la baraja
 *
 * @param card Carta que va a mostrarse
 * @param showReverseCard Si no quedan mas cartas en la baraja o todavia no se ha sacado una carta de la baraja se muestra el reverso de una carta
 */
@Composable
fun HCCardPrinter(
    card: Carta,
    showReverseCard: Boolean
    ) {
    if (!showReverseCard){
        Image(
            modifier = Modifier
                .height(300.dp),
            painter = painterResource(id = card.IdDrawable),
            contentDescription = null
        )
    } else {
        Image(
            modifier = Modifier
                .height(300.dp),
            painter = painterResource(id = R.drawable.creverso),
            contentDescription = null
        )
    }

}