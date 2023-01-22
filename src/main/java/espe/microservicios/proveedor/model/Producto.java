package espe.microservicios.proveedor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    private String id;
    private String nombre;
    private Double precio;
    private String descripcion;
}