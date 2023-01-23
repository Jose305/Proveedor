package espe.microservicios.proveedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import espe.microservicios.proveedor.model.CalificacionProducto;


public interface CalificacionProductoRepository extends MongoRepository<CalificacionProducto, String> {
}
