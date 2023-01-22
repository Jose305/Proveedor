package espe.microservicios.proveedor.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionProducto {
    @Id
    private String id;

    private String idEmpleado;
    private String descripcion;
    private Integer calificacion;
    private Date fechaCalificacion;
}
