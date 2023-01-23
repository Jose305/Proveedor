package espe.microservicios.proveedor.controller;

import espe.microservicios.proveedor.model.CalificacionProducto;
import espe.microservicios.proveedor.services.interfaces.CalificacionProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/calificacion-producto")
public class CalificacionProductoController {

    private CalificacionProductoService calificacionProductoService;

    @PostMapping(path = "")
    public ResponseEntity<CalificacionProducto> createNewCalificacionForProducto(
            @RequestParam(value = "idProveedor", required = true) String idProveedor,
            @RequestBody CalificacionProducto calificacionData) {
        CalificacionProducto newDbCalificacion = this.calificacionProductoService.createNewCalificacion(idProveedor,
                calificacionData);
        if (newDbCalificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(newDbCalificacion);
    }

    @GetMapping(path = "{idCalificacion}")
    public ResponseEntity<CalificacionProducto> getProductById(@PathVariable("idCalificacion") String idCalificacion) {
        CalificacionProducto calificacionProducto = this.calificacionProductoService
                .getCalificacionById(idCalificacion);
        if (calificacionProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calificacionProducto);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<CalificacionProducto>> getAllProductsFromProducto(
            @RequestParam("idProveedor") String idProveedor) {
        List<CalificacionProducto> producto = this.calificacionProductoService
                .getAllCalificacionesFromProducto(idProveedor);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(producto);
    }

    @PutMapping(path = "")
    public ResponseEntity<CalificacionProducto> updateProduct(@RequestBody CalificacionProducto dataCalificacion) {
        CalificacionProducto calificacion = this.calificacionProductoService.updateCalificacion(dataCalificacion);
        if (calificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(calificacion);
    }

    @DeleteMapping(path = "/{idCalificacion}")
    public ResponseEntity<CalificacionProducto> deleteProduct(
            @RequestParam("idProveedor") String idProveedor,
            @PathVariable("idCalificacion") String idCalificacion) {
        CalificacionProducto calificacion = this.calificacionProductoService.deleteCalificacion(idProveedor,
                idCalificacion);
        if (calificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(calificacion);
    }

}
