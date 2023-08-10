package com.acszo.newtpl.ui.nav

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.University,
        BottomNavItem.Station
    )
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.clip(RoundedCornerShape(26.dp)),
                label = { Text(text = item.route) },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route)  navController.navigate(item.route) else return@NavigationBarItem
                }
            )
        }
    }
}