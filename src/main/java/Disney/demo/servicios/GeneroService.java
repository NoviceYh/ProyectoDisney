
package Disney.demo.servicios;

import Disney.demo.entidades.Genero;
import Disney.demo.errores.ErrorService;
import Disney.demo.repositorios.GeneroRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;
    
    @Transactional
    public Genero save(Genero genero) throws ErrorService{
        
        validar(genero);
        
        return generoRepository.save(genero);
    }
    
    public void validar(Genero genero) throws ErrorService{
        if (genero.getImagen() == null) {
            throw new ErrorService("La imagen no puede ser nula");
        }
        if (genero.getNombre().isEmpty() || genero.getNombre() == null) {
            throw new ErrorService(("El nombre no puede ser nulo"));
        }
    }
    
}
