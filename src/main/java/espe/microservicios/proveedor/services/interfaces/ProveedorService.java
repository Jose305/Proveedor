package espe.microservicios.proveedor.services.interfaces;

import java.util.List;

import espe.microservicios.proveedor.model.Proveedor;

public interface ProveedorService {
    Proveedor createNewProveedor(Proveedor newProveedor);

    Proveedor updateProveedor(Proveedor dataProveedor);

    Proveedor deleteProveedor(String idProveedor);

    Proveedor getProveedorById(String idProveedor);

    List<Proveedor> getAllProveedors();
}
