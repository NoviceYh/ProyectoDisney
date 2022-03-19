
package Disney.demo.repositorios;

import Disney.demo.entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, String>{
    
    @Query("select c from Pelicula c where c.titulo like :q")
    List<Pelicula> findByTitle(@Param("q") String q);
    
}
