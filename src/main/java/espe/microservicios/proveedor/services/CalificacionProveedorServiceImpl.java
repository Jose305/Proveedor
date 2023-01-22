package espe.microservicios.proveedor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import espe.microservicios.proveedor.model.CalificacionProveedor;
import espe.microservicios.proveedor.model.Proveedor;
import espe.microservicios.proveedor.repository.CalificacionProveedorRepository;
import espe.microservicios.proveedor.repository.ProveedorRepository;
import espe.microservicios.proveedor.services.interfaces.CalificacionProveedorService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalificacionProveedorServiceImpl implements CalificacionProveedorService {

    private CalificacionProveedorRepository calificacionProveedorRepository;
    private ProveedorRepository proveedorRepository;

    @Override
    public CalificacionProveedor createNewCalificacion(String idProveedor, CalificacionProveedor calificacion) {
        Proveedor dbProveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (dbProveedor == null) {
            return null;
        }
        this.calificacionProveedorRepository.save(calificacion);
        List<CalificacionProveedor> calificaciones = dbProveedor.getCalificacionProveedores();
        calificaciones.add(calificacion);
        dbProveedor.setCalificacionProveedores(calificaciones);
        this.proveedorRepository.save(dbProveedor);
        return calificacion;
    }

    @Override
    public CalificacionProveedor deleteCalificacion(String idProveedor, String idCalificacion) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }
        List<CalificacionProveedor> list = proveedor.getCalificacionProveedores();
        CalificacionProveedor calificacion = list
                .stream()
                .filter(cal -> cal.getId().equals(idCalificacion))
                .findFirst().orElse(null);
        if (calificacion == null) {
            return null;
        }

        this.calificacionProveedorRepository.delete(calificacion);
        list = list
                .stream()
                .filter(cal -> !cal.getId().equals(idCalificacion))
                .toList();
        proveedor.setCalificacionProveedores(list);
        this.proveedorRepository.save(proveedor);

        calificacion.setId(null);
        return calificacion;
    }

    @Override
    public List<CalificacionProveedor> getAllCalificacionesFromProveeedor(String idProveedor) {
        Proveedor proveedor = this.proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }

        return proveedor.getCalificacionProveedores();
    }

    @Override
    public CalificacionProveedor getCalificacionById(String idCalificacion) {
        return this.calificacionProveedorRepository.findById(idCalificacion).orElse(null);
    }

    @Override
    public CalificacionProveedor updateCalificacion(CalificacionProveedor calificacion) {
        CalificacionProveedor dbCalificacion = this.calificacionProveedorRepository
                .findById(calificacion.getId())
                .orElse(null);
        if (dbCalificacion == null) {
            return null;
        }
        dbCalificacion.setCalificacion(calificacion.getCalificacion());
        dbCalificacion.setDescripcion(calificacion.getDescripcion());
        dbCalificacion.setFechaCalificacion(calificacion.getFechaCalificacion());
        dbCalificacion.setIdEmpleado(calificacion.getIdEmpleado());
        
        return this.calificacionProveedorRepository.save(dbCalificacion);
    }

}
