package com.example.coincapapp.views

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coincapapp.navigation.BottomNavigationItem
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {BottomTabBar(navController)}
    ){
        NavigationGraph(navController)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController, startDestination = BottomNavigationItem.Home.route){
        composable (BottomNavigationItem.Home.route) { AssetsList() }
        composable (BottomNavigationItem.Favourites.route) { Favourites() }
        composable (BottomNavigationItem.Settings.route) { Settings() }
    }
}

@Composable
fun BottomTabBar(navController: NavHostController){
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Favourites,
        BottomNavigationItem.Settings,
    )

    BottomAppBar {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route

        items.forEach { barItem ->
            val selected = barItem.route == currentRoute
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(barItem.route){
                        navController.graph.startDestinationRoute.let{route ->
                            if(route != null){
                                popUpTo(route){
                                    saveState = true
                                }
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) barItem.selectedIcon else barItem.unselectedIcon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}