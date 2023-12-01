package com.gmulbat1301.blackjack.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gmulbat1301.blackjack.R
import com.gmulbat1301.blackjack.clases.Baraja
import com.gmulbat1301.blackjack.clases.Carta
import com.gmulbat1301.blackjack.clases.Jugador
import com.gmulbat1301.blackjack.routes.Routes

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ScreenPVP(navController: NavHostController) {

    val baraja = Baraja
    baraja.crearBaraja()
    baraja.barajar()

    val jugador1 = Jugador()
    val manoJ1 by remember { mutableStateOf(jugador1.mano)}


    val jugador2 = Jugador()
    var manoJ2 by remember { mutableStateOf(jugador2.mano)}


    var turnoNombre by rememberSaveable { mutableStateOf("") }
    turnoNombre = jugador1.nombre

    var mostrarManoJ1 by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopRow(navController)

        Spacer(dp = 5)

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = "Turno de $turnoNombre",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }


        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    print(manoJ1.size)
                    val ultimaCarta = baraja.cogerCarta()
                    manoJ1.add(ultimaCarta)
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
                onClick = {

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
                text = jugador1.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(dp = 5)


            MostrarCarta(manoJ1)

        }

    }
}

//////////////////

@Composable
fun MostrarCarta(manoJugador: MutableList<Carta>){
    LazyRow{
        items(manoJugador){carta ->
            ImagenCarta(foto = carta.IdDrawable)
        }
    }
}

@Composable
fun ImagenCarta(foto: Int) {
    Image(
        painter = painterResource(id = foto ),
        contentDescription = null
    )
}
fun pedirCarta(){

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
}


