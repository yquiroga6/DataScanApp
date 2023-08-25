package gio.quiroga.datascantest1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Contract for information needed on every Rally navigation destination
 */

interface DSDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Rally app navigation destinations
 */
object ClientId : DSDestination {
    override val icon = Icons.Filled.Person
    override val route = "cliente"
}

object Products : DSDestination {
    override val icon = Icons.Filled.List
    override val route = "productos"
}

object Bills : DSDestination {
    override val icon = Icons.Filled.ShoppingCart
    override val route = "cuenta"
}

// Screens to be displayed in the top DataScanTabRow
val DSTabRowScreens = listOf(Products, Bills)