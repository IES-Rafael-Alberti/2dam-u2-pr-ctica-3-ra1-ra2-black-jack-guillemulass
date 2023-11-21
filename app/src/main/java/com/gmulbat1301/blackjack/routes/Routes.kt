package com.gmulbat1301.blackjack.routes

sealed class Routes(val route : String) {

    object mainScreen: Routes("mainScreen")
    object screenPVE: Routes("screenPVE")
    object screenPVP: Routes("screenPVP")
    object screenRules: Routes("screenRules")


}