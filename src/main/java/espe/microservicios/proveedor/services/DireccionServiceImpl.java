package espe.microservicios.proveedor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import espe.microservicios.proveedor.model.Direcciones;
import espe.microservicios.proveedor.model.Proveedor;
import espe.microservicios.proveedor.repository.DireccionRepository;
import espe.microservicios.proveedor.repository.ProveedorRepository;
import espe.microservicios.proveedor.services.interfaces.DireccionService;
import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class DireccionServiceImpl implements DireccionService {

    private DireccionRepository direccionRepository;
    private ProveedorRepository proveedorRepository;

    @Override
    public Direcciones createNewDireccion(String idProveedor, Direcciones newDireccion) {
        Proveedor dbProveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (dbProveedor == null) {
            return null;
        }
        this.direccionRepository.save(newDireccion);

        List<Direcciones> proveedorDireccion = dbProveedor.getDireccion();
        proveedorDireccion.add(newDireccion);
        dbProveedor.setDireccion(proveedorDireccion);

        this.proveedorRepository.save(dbProveedor);

        return newDireccion;
    }

    @Override
    public Direcciones deleteDireccion(String idProveedor, String idDireccion) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }

        List<Direcciones> list = proveedor.getDireccion();
        Direcciones direccion = list
                .stream()
                .filter(prod -> prod.getId().equals(idDireccion))
                .findFirst().orElse(null);
        if (direccion == null) {
            return null;
        }

        this.direccionRepository.delete(direccion);

        list = list
                .stream()
                .filter(prod -> !prod.getId().equals(idDireccion))
                .toList();

        proveedor.setDireccion(list);
        this.proveedorRepository.save(proveedor);

        direccion.setId(null);
        return direccion;
    }

    @Override
    public List<Direcciones> getAllDireccionFromProveedor(String idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }

        return proveedor.getDireccion();
    }

    @Override
    public Direcciones getDireccionById(String idDireccion) {
        return this.direccionRepository.findById(idDireccion).orElse(null);
    }

    @Override
    public Direcciones updateDireccion(Direcciones dataDireccion) {
        Direcciones dbDireccion = this.direccionRepository.findById(dataDireccion.getId()).orElse(null);
        if (dbDireccion == null) {
            return null;
        }
        dbDireccion.setDireccion(dataDireccion.getDireccion());
        dbDireccion.setReferencia(dataDireccion.getReferencia());
        return this.direccionRepository.save(dbDireccion);
    }


}
