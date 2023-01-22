package espe.microservicios.proveedor.services.interfaces;

import java.util.List;

import espe.microservicios.proveedor.model.CalificacionProveedor;

public interface CalificacionProveedorService {
    CalificacionProveedor createNewCalificacion(String idProveedor, CalificacionProveedor calificacion);

    CalificacionProveedor updateCalificacion(CalificacionProveedor calificacion);

    CalificacionProveedor deleteCalificacion(String idProveedor, String idCalificacion);

    CalificacionProveedor getCalificacionById(String idCalificacion);

    List<CalificacionProveedor> getAllCalificacionesFromProveeedor(String idProveedor);
}
