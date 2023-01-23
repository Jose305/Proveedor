package espe.microservicios.proveedor.controller;

import espe.microservicios.proveedor.model.Direcciones;
import espe.microservicios.proveedor.model.Producto;
import espe.microservicios.proveedor.services.interfaces.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(path = "/api/direccion")
public class DireccionesController {

    private DireccionService direccionService;

    @PostMapping(path = "")
    public ResponseEntity<Direcciones> createNewDireccionForProveedor(
            @RequestParam(value = "idProveedor", required = true) String idProveedor,
            @RequestBody Direcciones newDireccion) {
        Direcciones newDBDireccion = this.direccionService.createNewDireccion(idProveedor, newDireccion);
        if (newDBDireccion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(newDBDireccion);
    }

    @GetMapping(path = "{idDireccion}")
    public ResponseEntity<Direcciones> getDireccionById(@PathVariable("idDireccion") String idDireccion) {
        Direcciones direccion = this.direccionService.getDireccionById(idDireccion);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(direccion);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Direcciones>> getAllDireccionsFromProveedor(
            @RequestParam("idProveedor") String idProveedor) {
        List<Direcciones> direccion = this.direccionService.getAllDireccionsFromProveedor(idProveedor);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(direccion);
    }

    @PutMapping(path = "")
    public ResponseEntity<Direcciones> updateDireccion(@RequestBody Direcciones dataDireccion) {
        Direcciones direccion = this.direccionService.updateDireccion(dataDireccion);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(direccion);
    }

    @DeleteMapping(path = "{idDireccion}")
    public ResponseEntity<Direcciones> deleteDireccion(
            @RequestParam("idProveedor") String idProveedor,
            @PathVariable("idDireccion") String idDireccion) {
        Direcciones direccion = this.direccionService.deleteDireccion(idProveedor, idDireccion);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(direccion);
    }
}
