package gio.quiroga.datascantest1.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    // App state
    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    private val savedClientId: StateFlow<String> =
        savedStateHandle.getStateFlow(uiState.value.USER_ID, initialValue = "XXX")

    fun getClientId(): String{
        //return savedClientId.value
        return MAppState.d
    }

}