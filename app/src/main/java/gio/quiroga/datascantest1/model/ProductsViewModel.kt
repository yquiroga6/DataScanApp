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

class ProductsViewModel : ViewModel() {
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
    var isLoading by mutableStateOf(false)
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
    fun getProductById() {
        isLoading = true
        val call = viewModelScope.launch {
            try {
                addProductToState(DataScanApi.retrofitService.getProducto(textFieldValue))
                verified = true
                error = false
                showBottomSheet = false
            } catch (e: Exception) {
                error = true
            }
        }
        call.invokeOnCompletion { isLoading = false }
    }

    fun addProductToState(producto: Producto) {
        AppState.productos.add(producto)
        _uiState.value = ProductsState(products = AppState.productos)
    }

    fun getProductsFromState(): List<Producto>{
        return AppState.productos
    }

    /**
     * Clear data
     */
    fun initData(){
        textFieldValue = ""
    }
}


