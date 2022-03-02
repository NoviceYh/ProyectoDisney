
package Disney.demo.servicios;

import Disney.demo.entidades.Usuario;
import Disney.demo.enums.Rol;
import Disney.demo.errores.ErrorService;
import Disney.demo.repositorios.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Usuario save(String nombre, String apellido, String mail, String dni, String contraseña,
            String contraseña2, String username) throws ErrorService{
        
        validar(nombre, apellido, mail, dni, contraseña, contraseña2, username);
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setRol(Rol.USER);
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(contraseña));
        
        return usuarioRepository.save(usuario);        
    }
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepository.findByUsername(username);
            User user;
            
            List<GrantedAuthority> authorities = new ArrayList<>();
            
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol()));
            
            return new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

    private void validar(String nombre, String apellido, String mail, String dni, 
            String contraseña, String contraseña2,String username) throws ErrorService {
        
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            throw new ErrorService("El nombre de usuario ya existe");
        }
        
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorService("El Nombre no puede ser nulo");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new ErrorService("El Apellido no puede ser nulo");
        }
        if (dni.isEmpty() || dni == null) {
            throw new ErrorService("El Dni no puede ser nulo");
        }
        if (mail.isEmpty() || mail == null) {
            throw new ErrorService("El Mail no puede ser nulo");
        }
        if (contraseña.isEmpty() || contraseña == null) {
            throw new ErrorService("La Contraseña no puede ser nula");
        }
        if (contraseña.isEmpty() || contraseña == null || contraseña2.isEmpty() || contraseña2 == null) {
            throw new ErrorService("La contraseña no puede ser nula");
        }
        if (!contraseña.equals(contraseña2)) {
            throw new ErrorService("Las contraseñas no coinciden");
        }
    }
}
    
    

