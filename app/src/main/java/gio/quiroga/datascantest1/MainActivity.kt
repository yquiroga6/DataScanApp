package gio.quiroga.datascantest1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gio.quiroga.datascantest1.ui.theme.DataScanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DataScanApp()
        }
    }
}

@Composable
fun DataScanApp() {
    DataScanTheme {
        val navController = rememberNavController()
        /*val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            DSTabRowScreens.find { it.route == currentDestination?.route } ?: ClientId
*/        Scaffold(
        ) { innerPadding ->
            DSNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}