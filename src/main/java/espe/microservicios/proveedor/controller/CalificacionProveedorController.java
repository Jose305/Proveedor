package espe.microservicios.proveedor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import espe.microservicios.proveedor.model.CalificacionProveedor;
import espe.microservicios.proveedor.services.interfaces.CalificacionProveedorService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/calificacion-proveedor")
public class CalificacionProveedorController {
    private CalificacionProveedorService calificacionProveedorService;

    @PostMapping(path = "")
    public ResponseEntity<CalificacionProveedor> createNewCalificacionForProveedor(
            @RequestParam(value = "idProveedor", required = true) String idProveedor,
            @RequestBody CalificacionProveedor calificacionData) {
        CalificacionProveedor newDbCalificacion = this.calificacionProveedorService.createNewCalificacion(idProveedor,
                calificacionData);
        if (newDbCalificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(newDbCalificacion);
    }

    @GetMapping(path = "{idCalificacion}")
    public ResponseEntity<CalificacionProveedor> getProductById(@PathVariable("idCalificacion") String idCalificacion) {
        CalificacionProveedor calificacionProveedor = this.calificacionProveedorService
                .getCalificacionById(idCalificacion);
        if (calificacionProveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calificacionProveedor);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<CalificacionProveedor>> getAllProductsFromProveedor(
            @RequestParam("idProveedor") String idProveedor) {
        List<CalificacionProveedor> producto = this.calificacionProveedorService
                .getAllCalificacionesFromProveeedor(idProveedor);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(producto);
    }

    @PutMapping(path = "")
    public ResponseEntity<CalificacionProveedor> updateProduct(@RequestBody CalificacionProveedor dataCalificacion) {
        CalificacionProveedor calificacion = this.calificacionProveedorService.updateCalificacion(dataCalificacion);
        if (calificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(calificacion);
    }

    @DeleteMapping(path = "/{idCalificacion}")
    public ResponseEntity<CalificacionProveedor> deleteProduct(
            @RequestParam("idProveedor") String idProveedor,
            @PathVariable("idCalificacion") String idCalificacion) {
        CalificacionProveedor calificacion = this.calificacionProveedorService.deleteCalificacion(idProveedor,
                idCalificacion);
        if (calificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(calificacion);
    }

}
