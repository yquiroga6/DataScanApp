package gio.quiroga.datascantest1.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gio.quiroga.datascantest1.services.DataScanApi
import gio.quiroga.datascantest1.services.data_models.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {
    // App state
    private val _uiState = MutableStateFlow(ProductsState())
    val uiState: StateFlow<ProductsState> = _uiState.asStateFlow()

    var showBottomSheet by mutableStateOf(false)
        private set
    var textFieldValue by mutableStateOf("")
        private set
    var verified by mutableStateOf(false)
        private set
    var error by mutableStateOf(false)
        private set
    var productos = mutableStateOf<MutableList<Producto>>(mutableListOf())
        private set

    /**
     * Change the state of the bottomsheet
     */
    fun isShowingBottomSheet(state: Boolean) {
        showBottomSheet = state
    }

    /**
     * Change TextField of client id
     */
    fun changeTextFieldValue(productId: String) {
        textFieldValue = productId
    }

    /**
     * Get product from backend and stores in an array
     */
    fun getProductById()  {
        viewModelScope.launch {
            try {
                addProductToState(DataScanApi.retrofitService.getProducto(textFieldValue))
                verified = true
                error = false
                showBottomSheet = false
            } catch (e: Exception) {
                error = true
            }
        }
    }

    fun addProductToState(producto: Producto){
        productos.value.add(producto)
        AppState.productos.add(producto)
        _uiState.value = ProductsState(products =  productos.value)
    }
}


