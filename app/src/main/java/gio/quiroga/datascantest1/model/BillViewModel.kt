package gio.quiroga.datascantest1.model

import androidx.lifecycle.ViewModel
import gio.quiroga.datascantest1.services.data_models.Producto

class BillViewModel : ViewModel() {
    // App state
    /*private val _uiState = MutableStateFlow(ProductsState())
    val uiState: StateFlow<ProductsState> = _uiState.asStateFlow()*/


    fun getClientName(): String? {
        return AppState.cliente.nombre
    }

    fun getProductsSizefromState(): Int {
        return AppState.productos.size
    }

    /**
     * Group the products by Id
     */
    fun getGroupsById(): Map<String, List<Producto>> {
        return AppState.productos.groupBy { it.id }
    }

    /**
     * Sum values of the  elements in the group
     */
    fun sumSubtotal(): Int {
        return AppState.productos.sumOf { it.valor ?: 0 }
    }



}