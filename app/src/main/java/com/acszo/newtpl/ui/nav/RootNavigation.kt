package com.acszo.newtpl.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.acszo.newtpl.ui.BusPage
import com.acszo.newtpl.ui.nav.Pages.station
import com.acszo.newtpl.ui.nav.Pages.university

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = university) {
        composable(university) {
            BusPage("70C37")
        }
        composable(station) {
            BusPage("UD504")
        }
    }
}