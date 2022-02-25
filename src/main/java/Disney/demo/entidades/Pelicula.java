
package Disney.demo.entidades;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PELICULAS")
public class Pelicula {
    
    //ATRIBUTOS
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "Imagen", nullable = false)
    private byte[] imagen;
    
    @Column(name = "Titulo", nullable = false)
    private String titulo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha_de_creacion", nullable = false)
    private Date fechaCreacion;
    
    @Column(name = "Calificacion", nullable = false)
    private Integer calificacion;
    
    @OneToMany
    @Column(name = "Personajes", nullable = false)
    private List<Personaje> personajes;
    
    
    //CONSTRUCTORES
    public Pelicula() {
    }

    public Pelicula(String id, byte[] imagen, String titulo, Date fechaCreacion, Integer calificacion, List<Personaje> personajes) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
    
    
}
