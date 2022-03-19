
package Disney.demo.repositorios;

import Disney.demo.entidades.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, String> {
    
    @Query("select c from Personaje c where c.nombre like :q")
    List<Personaje> findByName(@Param("q") String q);
    
    @Query("select c from Personaje c where c.edad like :q")
    List<Personaje> findByEdad(@Param("q") String q);
    
}
