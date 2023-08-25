package gio.quiroga.datascantest1.model

import gio.quiroga.datascantest1.services.data_models.Cliente
import gio.quiroga.datascantest1.services.data_models.Producto

object AppState{
    var cliente: Cliente = Cliente(cedula = "")
    var productos: MutableList<Producto> = mutableListOf()
}