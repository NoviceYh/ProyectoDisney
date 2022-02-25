
package Disney.demo.servicios;

import Disney.demo.entidades.Personaje;
import Disney.demo.errores.ErrorService;
import Disney.demo.repositorios.PersonajeRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Transactional
    public Personaje save(Personaje personaje) throws ErrorService{
        
        validar(personaje);
        
        return personajeRepository.save(personaje);
    }
    
    public void validar(Personaje personaje) throws ErrorService{
        if (personaje.getNombre() == null || personaje.getNombre().isEmpty()) {
            throw new ErrorService("El campo nombre no puede ser nulo");
        }
        if (personaje.getPeso()== null || personaje.getPeso().isEmpty()) {
            throw new ErrorService("El campo peso no puede ser nulo");
        }
        if (personaje.getEdad()== null || personaje.getEdad().isEmpty()) {
            throw new ErrorService("El campo edad no puede ser nulo");
        }
        if (personaje.getHistoria()== null || personaje.getHistoria().isEmpty()) {
            throw new ErrorService("El campo historia no puede ser nulo");
        }
        if (personaje.getImagen()== null) {
            throw new ErrorService("Debe subir una imagen");
        }
    }
    
}
