package espe.microservicios.proveedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import espe.microservicios.proveedor.model.Direcciones;


public interface DireccionRepository extends MongoRepository<Direcciones, String> {
}
