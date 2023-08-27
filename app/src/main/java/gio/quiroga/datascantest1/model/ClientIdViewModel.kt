package gio.quiroga.datascantest1.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gio.quiroga.datascantest1.services.DataScanApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClientIdViewModel : ViewModel() {
    // App state
    private val _uiState = MutableStateFlow(ProductsState())
    val uiState: StateFlow<ProductsState> = _uiState.asStateFlow()

    var textFieldValue by mutableStateOf("")
        private set
    var isVerified by mutableStateOf(false)
        private set
    var error by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set

    /**
     * Sets TextField of client id
     */
    fun changeTextFieldValue(clientId: String) {
        textFieldValue = clientId
    }
    /**
     * Sets verified of client id
     */
    /**
     * Change TextField of client id
     */
    fun changeVerified(value: Boolean) {
        isVerified = value
    }


    /**
     * Verify client id from backend
     */
    fun verifyClientId()  {
        isLoading = true
        val call = viewModelScope.launch {
            try {
                AppState.cliente = DataScanApi.retrofitService.getCliente(textFieldValue)
                isVerified = true
                error = false
            } catch (e: Exception) {
                error = true
            }
        }
        call.invokeOnCompletion { isLoading = false }
    }

    /**
     * Clear data
     */
    fun clearData(){
        AppState.clearValues()
        textFieldValue = ""
    }

}
