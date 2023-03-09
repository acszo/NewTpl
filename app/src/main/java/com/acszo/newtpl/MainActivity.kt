package com.acszo.newtpl

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acszo.newtpl.ui.nav.BottomNavigationBar
import com.acszo.newtpl.ui.nav.Pages.university
import com.acszo.newtpl.ui.nav.RootNavigation
import com.acszo.newtpl.ui.theme.NewTplTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: university

            NewTplTheme {
                val systemUiController = rememberSystemUiController()
                val systemColor: Color = MaterialTheme.colorScheme.surface
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemColor
                    )
                }
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = { Text(text = "Verso ${currentRoute.lowercase()}") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    },
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) {
                    RootNavigation(navController)
                }
            }
        }
    }
}