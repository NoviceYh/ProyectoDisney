
package Disney.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pelicula {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private byte[] imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;
    
    private List<Personaje> personajes;
}
