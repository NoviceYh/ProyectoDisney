
package Disney.demo.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "Fecha_de_creacion")
    private LocalDate fechaCreacion;
    
    @Column(name = "Calificacion", nullable = false)
    private Integer calificacion;
    
    @OneToMany
    @JoinColumn(name = "genero_id")
    private List<Genero> generos = new ArrayList<>();
    
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "peliculas_personajes", joinColumns = 
            @JoinColumn(name = "pelicula_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "personaje_id", nullable = false))
    private List<Personaje> personajes = new ArrayList<>();;
    
    
    //CONSTRUCTORES
    public Pelicula() {
    }

    public Pelicula(String id, byte[] imagen, String titulo, LocalDate fechaCreacion, Integer calificacion, List<Personaje> personajes) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
    }
    
    //GETTERS AND SETTERS

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
    
    
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
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
