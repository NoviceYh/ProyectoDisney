
package Disney.demo.controladores;

import Disney.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegistroController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("")
    public String registro(){
        return "registro-form";
    }
    
    @PostMapping("")
    public String save(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String dni,
            @RequestParam String mail, @RequestParam String username, @RequestParam String password,
            @RequestParam String password2, RedirectAttributes redirectAttributes,
            Model model){
        try {
            usuarioService.save(nombre, apellido, mail, dni, password, password2, username);
            redirectAttributes.addFlashAttribute("success", "El usuario se registro satisfactoriamente!");
            return "redirect:/";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "registro-form";
        }
    }
}
