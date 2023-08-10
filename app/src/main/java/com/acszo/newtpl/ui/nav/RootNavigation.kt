package com.acszo.newtpl.ui.nav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.acszo.newtpl.ui.BusPage
import com.acszo.newtpl.ui.nav.Pages.station
import com.acszo.newtpl.ui.nav.Pages.university

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = university) {
        navigationComposable(route = university) {
            BusPage(stopCode = "70C37")
        }
        navigationComposable(route = station) {
            BusPage(stopCode = "UD504")
        }
    }
}

fun NavGraphBuilder.navigationComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) = composable(
    route = route,
    enterTransition = {
        fadeIn(animationSpec = tween(durationMillis = 210, delayMillis = 90, easing = LinearOutSlowInEasing)) +
                scaleIn(initialScale = 0.8f, animationSpec = tween(durationMillis = 300))
    },
    exitTransition = {
        fadeOut(animationSpec = tween(durationMillis = 90, easing = FastOutLinearInEasing)) +
                scaleOut(targetScale = 1.1f, animationSpec = tween(durationMillis = 300))
    },
    content = content
)