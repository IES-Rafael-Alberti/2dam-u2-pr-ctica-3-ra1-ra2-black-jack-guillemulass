package com.gmulbat1301.blackjack.routes

sealed class Routes(val route : String) {

    object MainScreen: Routes("mainScreen")
    object ScreenPVE: Routes("screenPVE")
    object ScreenPVP: Routes("screenPVP")
    object ScreenRules: Routes("screenRules")


}