package espe.microservicios.proveedor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import espe.microservicios.proveedor.model.Producto;
import espe.microservicios.proveedor.model.Proveedor;
import espe.microservicios.proveedor.repository.ProductoRepository;
import espe.microservicios.proveedor.repository.ProveedorRepository;
import espe.microservicios.proveedor.services.interfaces.ProductoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private ProductoRepository productoRepository;
    private ProveedorRepository proveedorRepository;

    @Override
    public Producto createNewProducto(String idProveedor, Producto newProducto) {
        Proveedor dbProveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (dbProveedor == null) {
            return null;
        }
        this.productoRepository.save(newProducto);

        List<Producto> proveedorProducts = dbProveedor.getProductos();
        proveedorProducts.add(newProducto);
        dbProveedor.setProductos(proveedorProducts);

        this.proveedorRepository.save(dbProveedor);

        return newProducto;
    }

    @Override
    public Producto deleteProducto(String idProveedor, String idProducto) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }

        List<Producto> list = proveedor.getProductos();
        Producto producto = list
                .stream()
                .filter(prod -> prod.getId().equals(idProducto))
                .findFirst().orElse(null);
        if (producto == null) {
            return null;
        } 

        this.productoRepository.delete(producto);

        list = list
                .stream()
                .filter(prod -> !prod.getId().equals(idProducto))
                .toList();
                
        proveedor.setProductos(list);
        this.proveedorRepository.save(proveedor);

        producto.setId(null);
        return producto;
    }

    @Override
    public List<Producto> getAllProductosFromProveedor(String idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }

        return proveedor.getProductos();
    }

    @Override
    public Producto getProductoById(String idProducto) {
        return this.productoRepository.findById(idProducto).orElse(null);
    }

    @Override
    public Producto updateProducto(Producto dataProducto) {
        Producto dbProducto = this.productoRepository.findById(dataProducto.getId()).orElse(null);
        if (dbProducto == null) {
            return null;
        }
        dbProducto.setDescripcion(dataProducto.getDescripcion());
        dbProducto.setNombre(dataProducto.getNombre());
        dbProducto.setPrecio(dataProducto.getPrecio());
        return this.productoRepository.save(dbProducto);
    }

}
