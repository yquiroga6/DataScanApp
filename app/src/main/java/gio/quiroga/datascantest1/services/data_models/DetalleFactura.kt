/**
 * Simple Inventory API
 * This is a simple API for a DataScan test
 *
 * OpenAPI spec version: 1.0.0
 * Contact: goldensoftworks@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package gio.quiroga.datascantest1.services.data_models


/**
 * 
 * @param numTRX 
 * @param producto 
 * @param cantidad 
 * @param precio 
 * @param descuento 
 * @param total 
 */
data class DetalleFactura (

    val numTRX: Int,
    val producto: String? = null,
    val cantidad: Int? = null,
    val precio: Int? = null,
    val descuento: java.math.BigDecimal? = null,
    val total: Int? = null
) {
}