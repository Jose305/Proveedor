package espe.microservicios.proveedor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import espe.microservicios.proveedor.model.CalificacionProducto;
import espe.microservicios.proveedor.model.Producto;
import espe.microservicios.proveedor.repository.CalificacionProductoRepository;
import espe.microservicios.proveedor.repository.ProductoRepository;
import espe.microservicios.proveedor.services.interfaces.CalificacionProductoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalificacionProductoServicelmpl implements CalificacionProductoService {

    private CalificacionProductoRepository calificacionProductoRepository;
    private ProductoRepository productoRepository;

    @Override
    public CalificacionProducto createNewCalificacion(String idProducto, CalificacionProducto calificacion) {
        Producto dbProducto = this.productoRepository.findById(idProducto).orElse(null);
        if (dbProducto == null) {
            return null;
        }
        this.calificacionProductoRepository.save(calificacion);
        List<CalificacionProducto> calificaciones = dbProducto.getCalificacionProducto();
        calificaciones.add(calificacion);
        dbProducto.setCalificacionProducto(calificaciones);
        this.productoRepository.save(dbProducto);
        return calificacion;
    }

    @Override
    public CalificacionProducto deleteCalificacion(String idProducto, String idCalificacion) {
        Producto producto = this.productoRepository.findById(idProducto).orElse(null);
        if (producto == null) {
            return null;
        }
        List<CalificacionProducto> list = producto.getCalificacionProducto();
        CalificacionProducto calificacion = list
                .stream()
                .filter(cal -> cal.getId().equals(idCalificacion))
                .findFirst().orElse(null);
        if (calificacion == null) {
            return null;
        }

        this.calificacionProductoRepository.delete(calificacion);
        list = list
                .stream()
                .filter(cal -> !cal.getId().equals(idCalificacion))
                .toList();
        producto.setCalificacionProducto(list);
        this.productoRepository.save(producto);

        calificacion.setId(null);
        return calificacion;
    }

    @Override
    public List<CalificacionProducto> getAllCalificacionesFromProducto(String idProducto) {
        Producto producto = this.productoRepository.findById(idProducto).orElse(null);
        if (producto == null) {
            return null;
        }

        return producto.getCalificacionProducto();
    }

    @Override
    public CalificacionProducto getCalificacionById(String idCalificacion) {
        return this.calificacionProductoRepository.findById(idCalificacion).orElse(null);
    }

    @Override
    public CalificacionProducto updateCalificacion(CalificacionProducto calificacion) {
        CalificacionProducto dbCalificacion = this.calificacionProductoRepository
                .findById(calificacion.getId())
                .orElse(null);
        if (dbCalificacion == null) {
            return null;
        }
        dbCalificacion.setCalificacion(calificacion.getCalificacion());
        dbCalificacion.setDescripcion(calificacion.getDescripcion());
        dbCalificacion.setFechaCalificacion(calificacion.getFechaCalificacion());
        dbCalificacion.setIdEmpleado(calificacion.getIdEmpleado());

        return this.calificacionProductoRepository.save(dbCalificacion);
    }
}
