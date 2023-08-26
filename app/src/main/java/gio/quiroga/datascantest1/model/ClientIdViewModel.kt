package gio.quiroga.datascantest1.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
    var verified by mutableStateOf(false)
        private set
    var error by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set

    /**
     * Change TextField of client id
     */
    fun changeTextFieldValue(clientId: String) {
        textFieldValue = clientId
    }


    /**
     * Verify client id from backend
     */
    fun verifyClientId()  {
        isLoading = true
        val call = viewModelScope.launch {
            try {
                AppState.cliente = DataScanApi.retrofitService.getCliente(textFieldValue)
                verified = true
                error = false
            } catch (e: Exception) {
                error = true
            }
        }
        call.invokeOnCompletion { isLoading = false }
    }

}
