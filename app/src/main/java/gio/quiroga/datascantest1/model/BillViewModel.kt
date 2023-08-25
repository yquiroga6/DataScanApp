package gio.quiroga.datascantest1.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

}