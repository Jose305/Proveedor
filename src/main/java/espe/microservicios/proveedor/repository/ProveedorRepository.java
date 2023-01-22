package espe.microservicios.proveedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import espe.microservicios.proveedor.model.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

}
