package com.gmulbat1301.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gmulbat1301.blackjack.ui.theme.BlackJackTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmulbat1301.blackjack.routes.Routes
import com.gmulbat1301.blackjack.userinterface.*


class MainActivity : ComponentActivity() {

    private val highestCardViewModel: HighestCardViewModel by viewModels()
    private val pvpViewModel: PVPViewModel by viewModels()
    private val pveViewModel: PVEViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackJackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.MainScreen.route
                    ) {

                        composable(Routes.MainScreen.route) {
                            MainScreen(
                                navController
                            )
                        }
                        composable(Routes.ScreenHC.route) {
                            HighestCardScreen(
                                navController,
                                highestCardViewModel
                            )
                        }
                        composable(Routes.ScreenPVP.route) {
                            ScreenPVP(
                                navController,
                                pvpViewModel
                            )
                        }
                        composable(Routes.ScreenPVE.route) {
                            ScreenPVE(
                                navController,
                                pveViewModel
                            )
                        }
                        composable(Routes.ScreenRules.route) {
                            screenRules(
                                navController
                            )
                        }

                    }
                }
            }
        }
    }
}