
package Disney.demo.repositorios;

import Disney.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    @Query("select c from Usuario c where c.username = :q")
    Usuario findByUsername(@Param("q") String q);
    
}
