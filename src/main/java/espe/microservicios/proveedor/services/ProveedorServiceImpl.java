package espe.microservicios.proveedor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import espe.microservicios.proveedor.model.Proveedor;
import espe.microservicios.proveedor.repository.ProveedorRepository;
import espe.microservicios.proveedor.services.interfaces.ProveedorService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    @Override
    public Proveedor createNewProveedor(Proveedor newProveedor) {
        newProveedor.setProductos(new ArrayList<>());
        newProveedor.setCalificacionProveedores(new ArrayList<>());
        Proveedor dbProveedor = this.proveedorRepository.save(newProveedor);
        return dbProveedor;
    }

    @Override
    public Proveedor deleteProveedor(String idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }
        this.proveedorRepository.delete(proveedor);
        proveedor.setId(null);
        return proveedor;
    }

    @Override
    public List<Proveedor> getAllProveedors() {
        return this.proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(String idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        return proveedor;
    }

    @Override
    public Proveedor updateProveedor(Proveedor dataProveedor) {
        Proveedor dbProveedor = this.proveedorRepository.findById(dataProveedor.getId()).orElse(null);
        if (dbProveedor == null) {
            return null;
        }
        dbProveedor.setCorreo(dataProveedor.getCorreo());
        dbProveedor.setNombre(dataProveedor.getNombre());
        dbProveedor.setTelefono(dataProveedor.getTelefono());
        return this.proveedorRepository.save(dbProveedor);
    }

}
