package com.gmulbat1301.blackjack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gmulbat1301.blackjack.R
import com.gmulbat1301.blackjack.routes.Routes

/**
 * Funcion Inicial, controla la navegacion
 *
 * @param navController Controlador de navegacion
 */
@Composable
fun MainScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(20)

        Text(
            text = "BlackJack",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(25)

        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
            Modifier.size(250.dp)
        )

        Spacer(40)

        NavButtons("Individual",navController)

        Spacer(20)

        NavButtons("Multijugador",navController)

        Spacer(20)

        NavButtons("Reglas",navController)

    }
}

/**
 * Funcion que crea un spacer, solo por simplificar el uso del spacer
 *
 * @param dp tamaño del spacer
 */
@Composable
fun Spacer(dp: Int){
    Spacer(modifier = Modifier.padding(dp.dp))
}

/**
 * Funcion que crea los botones de navegacion
 *
 * @param text Texto que se muestra en el boton,
 * tambien se usa para diferenciar unos botones de otros a la hora de crearlos
 * @param navController Controlador de navegacion del boton que se quiere crear
 */
@Composable
fun NavButtons(
    text: String,
    navController: NavHostController
){
    var fontSize = 40
    Button(
        shape = RectangleShape,
        onClick = {
            /**
             * Dependiendo del texto del boton se crea un boton u otro
             */
            when (text) {
                "Individual" -> navController.navigate(Routes.screenPVE.route)
                "Multijugador" -> navController.navigate(Routes.screenPVP.route)
                "Reglas" -> {
                    navController.navigate(Routes.screenRules.route)
                    fontSize = 25 }
            }
        },
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}