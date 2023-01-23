package espe.microservicios.proveedor.services.interfaces;

import java.util.List;

import espe.microservicios.proveedor.model.CalificacionProducto;


public interface CalificacionProductoService {

    CalificacionProducto createNewCalificacion(String idProducto, CalificacionProducto calificacion);

    CalificacionProducto updateCalificacion(CalificacionProducto calificacion);

    CalificacionProducto deleteCalificacion(String idProducto, String idCalificacion);

    CalificacionProducto getCalificacionById(String idCalificacion);

    List<CalificacionProducto> getAllCalificacionesFromProducto(String idProducto);
}
