
package Disney.demo.servicios;

import Disney.demo.entidades.Pelicula;
import Disney.demo.errores.ErrorService;
import Disney.demo.repositorios.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    public Pelicula save(Pelicula pelicula) throws ErrorService{
        
        validar(pelicula);
        
        return peliculaRepository.save(pelicula);
    }
    
    public void validar(Pelicula pelicula) throws ErrorService{
        if (pelicula.getFechaCreacion() == null) {
            throw new ErrorService("La Fecha no puede ser nula");
        }
        if (pelicula.getImagen()== null) {
            throw new ErrorService("La Imagen no puede ser nula");
        }
        if (pelicula.getTitulo()== null || pelicula.getTitulo().isEmpty()) {
            throw new ErrorService("El titulo no puede ser nulo");
        }
    }
}
