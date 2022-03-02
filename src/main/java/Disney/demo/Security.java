package Disney.demo;

import Disney.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*","/img/*","/js/*").permitAll() //Permitir todos los archivos css, img y js
                .and().formLogin()
                      .loginPage("/login") //Pagina de login
                      .usernameParameter("username") //Parametro de nombre de usuario
                      .passwordParameter("password") //Parametro de contraseña
                      .defaultSuccessUrl("/") //Redireccion luego de logearse
                      .loginProcessingUrl("/logincheck")
                      .failureUrl("/login?error=error") //Configuracion de error al login
                      .permitAll()
                .and().logout()
                      .logoutUrl("/logout") //Pagina de logout
                      .logoutSuccessUrl("/login?logout") //Redireccion luego del logout
                .and().csrf().disable();
    }
}
