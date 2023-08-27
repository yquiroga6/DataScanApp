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
        val min = AppState.productos.minBy { it.valor ?: 0 }.valor ?: 0
        if (sumSubtotal() > 40000) return min
        return 0
    }

}

enum class TradeCondition { ONE, THREE, UP }