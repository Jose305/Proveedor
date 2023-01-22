package espe.microservicios.proveedor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import espe.microservicios.proveedor.model.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

}
