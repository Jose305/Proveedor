package espe.microservicios.proveedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import espe.microservicios.proveedor.model.CalificacionProveedor;

public interface CalificacionProveedorRepository extends MongoRepository<CalificacionProveedor, String> {

}
