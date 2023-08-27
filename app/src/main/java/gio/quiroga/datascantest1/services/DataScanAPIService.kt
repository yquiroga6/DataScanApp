package gio.quiroga.datascantest1.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gio.quiroga.datascantest1.services.data_models.Cliente
import gio.quiroga.datascantest1.services.data_models.Factura
import gio.quiroga.datascantest1.services.data_models.Producto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:4000"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface DataScanApiService {
    /**
     * Returns a [Cliente] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "cliente" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("/cliente/{cedula}")
    suspend fun getCliente(@Path("cedula") cedula: String): Cliente

    /**
     * Returns a [Producto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "producto" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("/producto/{id}")
    suspend fun getProducto(@Path("id") id: String): Producto

    /**
     * Saves a [Factura] and this method can be called from a Coroutine.
     * The @POST annotation indicates that the "factura" endpoint will be requested with the POST
     * HTTP method
     */
    @POST("/factura")
   // @JvmSuppressWildcards
    suspend fun postFactura(@Body body: Factura): Response<Map<String, Any>>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object DataScanApi {
    val retrofitService: DataScanApiService by lazy { retrofit.create(DataScanApiService::class.java) }
}
