package espe.microservicios.proveedor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import espe.microservicios.proveedor.model.Proveedor;
import espe.microservicios.proveedor.services.interfaces.ProveedorService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/proveedor")
public class ProveedorController {
    private ProveedorService proveedorService;

    @PostMapping(value = "")
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor newProveedor) {
        Proveedor proveedor = this.proveedorService.createNewProveedor(newProveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Proveedor> getProvedorById(@PathVariable(value = "id") String idProveedor) {
        Proveedor proveedor = this.proveedorService.getProveedorById(idProveedor);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Proveedor>> getAllProvedors() {
        List<Proveedor> list = this.proveedorService.getAllProveedors();
        return ResponseEntity.ok(list);
    }

    @PutMapping(value = "")
    public ResponseEntity<Proveedor> updateProveedorData(@RequestBody Proveedor proveedorData) {
        Proveedor proveedor = this.proveedorService.updateProveedor(proveedorData);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Proveedor> deleteProvedorById(
        @PathVariable(value = "id") String idProveedor
    ) {
        Proveedor proveedor = this.proveedorService.deleteProveedor(idProveedor);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(proveedor);
    }
}
