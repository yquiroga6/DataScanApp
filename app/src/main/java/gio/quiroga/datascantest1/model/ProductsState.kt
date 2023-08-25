package gio.quiroga.datascantest1.model

import gio.quiroga.datascantest1.services.data_models.Producto

/*sealed interface AppState{
    data class Properties()
}*/
data class ProductsState(val products: MutableList<Producto> = mutableListOf(), val USER_ID: String = "UserId") {
}
