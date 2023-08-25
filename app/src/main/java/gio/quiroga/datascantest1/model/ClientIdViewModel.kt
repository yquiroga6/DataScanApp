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

class ClientIdViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    // App state
    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    private val savedClientId: StateFlow<String> =
        savedStateHandle.getStateFlow(key = uiState.value.USER_ID, initialValue = "LLL")

    var textFieldValue by mutableStateOf("")
        private set
    var verified by mutableStateOf(false)
        private set
    var error by mutableStateOf(false)
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
        savedStateHandle[uiState.value.USER_ID] = "1094885006"
        MAppState.d = "cosa"
        viewModelScope.launch {
            try {
                val cliente = DataScanApi.retrofitService.getCliente(textFieldValue)
                savedStateHandle[uiState.value.USER_ID] = "1094885006"
                verified = true
                error = false
            } catch (e: Exception) {
                error = true
            }
        }
    }

}
