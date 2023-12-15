package com.gmulbat1301.blackjack.routes

sealed class Routes(val route : String) {

    object MainScreen: Routes("mainScreen")
    object ScreenHC: Routes("screenHC")
    object ScreenPVP: Routes("screenPVP")
    object ScreenPVE: Routes("screenPVE")
    object ScreenRules: Routes("screenRules")


}