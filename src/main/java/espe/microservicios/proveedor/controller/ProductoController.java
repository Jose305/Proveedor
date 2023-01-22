package espe.microservicios.proveedor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import espe.microservicios.proveedor.model.Producto;
import espe.microservicios.proveedor.services.interfaces.ProductoService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/producto")
public class ProductoController {
    private ProductoService productoService;

    @PostMapping(path = "")
    public ResponseEntity<Producto> createNewProductForProveedor(
            @RequestParam(value = "idProveedor", required = true) String idProveedor,
            @RequestBody Producto newProduct) {
        Producto newDBProduct = this.productoService.createNewProducto(idProveedor, newProduct);
        if (newDBProduct == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(newDBProduct);
    }

    @GetMapping(path = "{idProduct}")
    public ResponseEntity<Producto> getProductById(@PathVariable("idProduct") String idProduct) {
        Producto producto = this.productoService.getProductoById(idProduct);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Producto>> getAllProductsFromProveedor(
            @RequestParam("idProveedor") String idProveedor) {
        List<Producto> producto = this.productoService.getAllProductosFromProveedor(idProveedor);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(producto);
    }

    @PutMapping(path = "")
    public ResponseEntity<Producto> updateProduct(@RequestBody Producto dataProduct) {
        Producto product = this.productoService.updateProducto(dataProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(path = "{idProduct}")
    public ResponseEntity<Producto> deleteProduct(
            @RequestParam("idProveedor") String idProveedor,
            @PathVariable("idProduct") String idProduct) {
        Producto product = this.productoService.deleteProducto(idProveedor, idProduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(product);
    }
}
