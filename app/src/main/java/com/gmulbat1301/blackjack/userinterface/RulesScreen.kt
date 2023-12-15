package com.gmulbat1301.blackjack.userinterface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.gmulbat1301.blackjack.routes.Routes


@Composable
fun screenRules(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Text(
            text = "Reglas",
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
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(
                text = "Valor de las cartas",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black

            )

            Text(
                text = "As -> 1/11 (Depende de la conveniencia a la mano del jugador)\n" +
                        "Valet -> 10\n" +
                        "Dama -> 10\n" +
                        "Rey -> 10",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(
                text = "Individual",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "El jugador puede pedir cartas hasta superar la puntuacion de 21\nEl crupier tiene que plantarse siempre que su  puntuacion supere 17, pero puede plantarse antes.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(
                text = "Multijugador",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Los jugadores pueden pedir cartas (simpre que su puntuacion no sea mayor de 21) y pasar el turno como quieran, si ambos pasan el turno se acaba la partida, si la puntuacion de ambos supera 21 se acaba la partida.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

    }
}