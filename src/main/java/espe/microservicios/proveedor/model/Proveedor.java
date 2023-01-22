package espe.microservicios.proveedor.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    private String id;
    private String nombre;
    private String telefono;
    private String correo;
    @DBRef
    @JsonIgnore
    private List<Producto> productos;
    @DBRef
    @JsonIgnore
    private List<Direcciones> direccion;
    @DBRef
    @JsonIgnore
    private List<CalificacionProveedor> calificacionProveedores;
}
