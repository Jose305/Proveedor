package espe.microservicios.proveedor.services.interfaces;

import espe.microservicios.proveedor.model.Direcciones;

import java.util.List;

public interface DireccionService {

    Direcciones createNewDireccion(String idProveedor, Direcciones newDireccion);

    Direcciones updateDireccion(Direcciones dataDireccion);

    Direcciones deleteDireccion(String idProveedor, String idDireccion);

    Direcciones getDireccionById(String idDireccion);

    List<Direcciones> getAllDireccionFromProveedor(String idProveedor);
}
