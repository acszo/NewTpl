package com.acszo.newtpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acszo.newtpl.ui.nav.BottomNavigationBar
import com.acszo.newtpl.ui.nav.Pages.university
import com.acszo.newtpl.ui.nav.RootNavigation
import com.acszo.newtpl.ui.theme.NewTplTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: university

            NewTplTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor: Color = MaterialTheme.colorScheme.surface
                val navigationBarColor: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor
                    )
                    systemUiController.setNavigationBarColor(
                        color = navigationBarColor
                    )
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Verso ${currentRoute.lowercase()}") },
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                scrolledContainerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    },
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        RootNavigation(navController)
                    }
                }
            }
        }
    }
}