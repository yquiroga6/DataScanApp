package gio.quiroga.datascantest1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gio.quiroga.datascantest1.ui.screens.BillScreen
import gio.quiroga.datascantest1.ui.screens.ClientIDScreen
import gio.quiroga.datascantest1.ui.screens.ProductsScreen

@Composable
fun DSNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ClientId.route,
        modifier = modifier
    ) {
        composable(route = ClientId.route) {
            ClientIDScreen(
                onSeeAllProducts = {
                    navController.navigateSingleTopTo(Products.route)
                },
            )
        }
        composable(route = Products.route) {
            ProductsScreen(
                onSeeBill = {
                    navController.navigateSingleTopTo(Bills.route)
                },
            )
        }
        composable(route = Bills.route) {
            BillScreen(
                onBackToProducts = {
                    navController.navigateSingleTopTo(Products.route)
                },
                goToClientScreen = {
                    navController.navigateSingleTopTo(ClientId.route)
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }