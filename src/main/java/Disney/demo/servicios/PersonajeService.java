
package Disney.demo.servicios;

import Disney.demo.entidades.Personaje;
import Disney.demo.repositorios.PersonajeRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Transactional
    public Personaje save(Personaje personaje){
        
        
        return personajeRepository.save(personaje);
    }
    
    public void validar(Personaje personaje){
        
    }
    
}
