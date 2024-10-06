package com.lightfeather.userswithfavorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lightfeather.userswithfavorite.ui.Destination
import com.lightfeather.userswithfavorite.ui.model.UiUser
import com.lightfeather.userswithfavorite.ui.page.userdetails.UserDetailPage
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersListPage
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersPageSource
import com.lightfeather.userswithfavorite.ui.theme.UsersWithFavoriteTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsersWithFavoriteTheme {
                val navController = rememberNavController()
                KoinContext {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomNavigationBar(navController)
                        }
                    ) { innerPadding ->
                        NavigationGraph(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Destination.USERS_LIST_ROUTE, modifier = modifier) {
        composable(Destination.USERS_LIST_ROUTE) {
            UsersListPage(UsersPageSource.ALL, navController)
        }
        composable(Destination.FAVORITE_USERS_ROUTE) {
            UsersListPage(UsersPageSource.FAVORITE, navController)
        }
        composable<UiUser> { backStackEntry ->
            UserDetailPage(backStackEntry.toRoute<UiUser>())
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Destination.USERS_LIST_ROUTE,
        Destination.FAVORITE_USERS_ROUTE
    )

    BottomNavigation {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { destination ->
            BottomNavigationItem(
                icon = {
                    when (destination) {
                        Destination.USERS_LIST_ROUTE -> Icon(Icons.AutoMirrored.Default.List, contentDescription = "Users List")
                        Destination.FAVORITE_USERS_ROUTE -> Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                        else -> {}
                    }
                },
                label = {
                    when (destination) {
                        Destination.USERS_LIST_ROUTE -> Text("Users")
                        Destination.FAVORITE_USERS_ROUTE -> Text("Favorites")
                        else -> {}
                    }
                },
                selected = currentDestination?.route == destination,
                onClick = {
                    navController.navigate(destination) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}