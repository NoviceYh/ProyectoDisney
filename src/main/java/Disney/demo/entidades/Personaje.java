
package Disney.demo.entidades;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PERSONAJES")
public class Personaje {
    
    //ATRIBUTOS
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;
    
    @Basic(fetch=FetchType.LAZY) //Para que no incluya el campo en una busqueda general
    @Lob
    @Column(name = "Imagen", nullable = false)
    private byte[] imagen;
    
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Column(name = "Edad", nullable = false)
    private String edad;
    
    @Column(name = "Peso", nullable = false)
    private String peso;
    
    @Column(name = "Historia", nullable = false)
    private String historia;
    
    @Column(name = "Peliculas", nullable = false)
    private List<Pelicula> peliculas;
    
    //CONTRUCTORES
    public Personaje() {
    }

    public Personaje(String id, byte[] imagen, String nombre, String edad, String peso, String historia, List<Pelicula> peliculas) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.peliculas = peliculas;
    }
    
    //GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<Pelicula> getPelicula() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    
    
    
    
}
