package espe.microservicios.proveedor.services.interfaces;

import java.util.List;

import espe.microservicios.proveedor.model.Producto;

public interface ProductoService {
    Producto createNewProducto(String idProveedor, Producto newProducto);

    Producto updateProducto(Producto dataProducto);

    Producto deleteProducto(String idProveedor, String idProducto);

    Producto getProductoById(String idProducto);

    List<Producto> getAllProductosFromProveedor(String idProveedor);

}
