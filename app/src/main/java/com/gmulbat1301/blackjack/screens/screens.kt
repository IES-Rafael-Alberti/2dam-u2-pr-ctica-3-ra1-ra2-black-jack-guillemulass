package com.gmulbat1301.blackjack.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gmulbat1301.blackjack.R
import com.gmulbat1301.blackjack.routes.Routes

@Composable
fun mainScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BlackJack",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(200.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.screenPVE.route) },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "Individual",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.screenPVP.route) },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "Multujugador",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.screenRules.route) },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "Reglas",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

    }
}

@Composable
fun screenPVE(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "Multijugador",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.mainScreen.route) },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Pantalla Inicial",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}

@Composable
fun screenPVP(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "Individual",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.mainScreen.route) },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Pantalla Inicial",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

    }
}

@Composable
fun screenRules(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondocasino), contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Text(
            text = "Reglas",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            shape = RectangleShape,
            onClick = { navController.navigate(Routes.mainScreen.route) },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Pantalla Inicial",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.padding(120.dp))

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        ){
            Text(
                text = "Individual",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "ASUDFAISODV SADUOP FU PSADFOU SDAFOU DSFOU SDFOHI SDFLHSDAFUHL DSFI SDFH "
            )
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Text(
                text = "Multijugador",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "WILLYREX SON LOS PADRES WILLYREX SON LOS PADRES WILLYREX SON LOS PADRES WILLYREX SON LOS PADRES"
            )
        }

    }
}