package gio.quiroga.datascantest1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gio.quiroga.datascantest1.services.DataScanApi
import gio.quiroga.datascantest1.services.data_models.Factura
import gio.quiroga.datascantest1.services.data_models.Producto
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BillViewModel : ViewModel() {
    // App state
    /*private val _uiState = MutableStateFlow(ProductsState())
    val uiState: StateFlow<ProductsState> = _uiState.asStateFlow()*/

    var isLoading by mutableStateOf(false)
        private set

    var billSaved by mutableStateOf(false)
        private set

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

    /**
     * Calculates discounts
     */
    fun calculateDiscounts(): Float {
        val discountsPercentage =
            getTradeCondition1Percentage() + getTradeCondition3Percentage()
        val discounts = getTradeConditionSince40000()
        return discounts + (AppState.productos.sumOf { it.valor ?: 0 } * discountsPercentage)
    }

    /**
     * Calculates Total value of purchase
     */
    fun calculateTotal(): Float {
        return AppState.productos.sumOf { it.valor ?: 0 } - calculateDiscounts()
    }

    /**
     * Verify if the trade condition 1 applies and return the percentage of discount
     */
    fun getTradeCondition1Percentage(): Float {
        if (getGroupsById().values.any { it.size > 1 }) return 0.2F
        return 0.0F
    }

    /**
     * Verify if the trade condition 3 applies and return the percentage of discount
     */
    fun getTradeCondition3Percentage(): Float {
        if (getGroupsById().size > 3) return 0.15F
        return 0.0F
    }

    /**
     * Verify if the trade condition since 40000 applies and return quantity of discount
     */
    fun getTradeConditionSince40000(): Int {
        val min =
            if (AppState.productos.isNotEmpty()) AppState.productos.minBy {
                it.valor ?: 0
            }.valor else 0
        if (sumSubtotal() > 40000) return min ?: 0
        return 0
    }

    /**
     * Save bill on database
     */
    fun saveBill() {
        isLoading = true
        val call = viewModelScope.launch {
            try {
                val date = LocalDateTime.now()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)//.split(".").first()
                val response = DataScanApi.retrofitService.postFactura(
                    Factura(
                        fecha = date,
                        cliente = AppState.cliente.cedula
                    )
                )
                print(response)
                billSaved = true
            } catch (e: Exception) {
                print(e)
            }
        }
        call.invokeOnCompletion { isLoading = false }
    }
}