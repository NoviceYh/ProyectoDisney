
package Disney.demo.entidades;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "GENEROS")
public class Genero {
    
    //ATRIBUTOS
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "Imagen", nullable = false)
    private byte[] imagen;
    
    @OneToMany
    @Column(name = "Peliculas", nullable = false)
    private List<Pelicula> peliculas;

    //CONSTRUCTORES
    public Genero() {
    }

    public Genero(String id, String nombre, byte[] imagen, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
    }
    
    //GETTERS AND SETTERS
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
