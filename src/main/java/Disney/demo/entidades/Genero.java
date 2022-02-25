
package Disney.demo.entidades;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

public class Genero {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private byte[] imagen;
    
    private List<Pelicula> peliculas;

    public Genero() {
    }

    public Genero(String id, String nombre, byte[] imagen, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
    
}
