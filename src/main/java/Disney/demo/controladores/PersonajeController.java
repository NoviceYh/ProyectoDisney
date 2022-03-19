package Disney.demo.controladores;

import Disney.demo.entidades.Personaje;
import Disney.demo.errores.ErrorService;
import Disney.demo.servicios.PersonajeService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("")
    public String list(Model model, @RequestParam(required = false) String name,
            @RequestParam(required = false) String age) {
        System.out.println("Nombre: " + name);
        System.out.println("Edad: " + age);
        //Buscar por nombre
        if (!(name == null || name.length() == 0)) {
            System.out.println("if nombre");
            model.addAttribute("personajes", personajeService.findByName(name));
            return "personaje-list";
        }else if(!(age == null || age.length() == 0)){
            System.out.println("if edad");
            model.addAttribute("personajes", personajeService.findByEdad(age));
            return "personaje-list";
        }else{
            System.out.println("if all");
            model.addAttribute("personajes", personajeService.findAll());
            return "personaje-list";
        }
        //Buscar por edad
//        if (edad == null || edad.toString().length() == 0) {
//            model.addAttribute("personajes", personajeService.findAll());
//        } else {
//            model.addAttribute("personajes", personajeService.findByEdad(edad));
//        }
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) String id) {
//        if (id != null) {
//            Personaje p = personajeService.findById(id);
//            if (p != null) {
//                model.addAttribute("personaje", p);
//            }else{
//                return "redirect:/characters";
//            }
//        }else{
        model.addAttribute("personaje", new Personaje());
//        }
        return "personaje-form";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Personaje personaje,
            RedirectAttributes redirectAttributes, @RequestParam(name = "foto") MultipartFile foto) {

        System.out.println("paso por aqui");
        byte[] contenido = null;

        //Verificamos que el archivo no este vacio
        if (!foto.isEmpty()) {
            //Verficiamos que el contenido del archivo sea una foto tipo jpg o png
            if (foto.getContentType().endsWith("jpeg") || foto.getContentType().endsWith("png") || foto.getContentType().endsWith("svg")) {
                try {
                    contenido = foto.getBytes();
                    personaje.setImagen(contenido);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            personajeService.save(personaje);
            redirectAttributes.addFlashAttribute("success", "El personaje se ha guardado con exito!");
            return "redirect:/characters";
        } catch (ErrorService ex) {
            model.addAttribute("error", ex.getMessage());
            return "personaje-form";
        }
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> fotoPublicacion(@PathVariable(value = "id") String id) {
        Personaje p = personajeService.findById(id);

        if (p.getImagen() != null) {

            HttpHeaders cabecera = new HttpHeaders();
            cabecera.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(p.getImagen(), cabecera, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable(name = "id") String id, Model model) {
        System.out.println("Entro al edit");
        Personaje p = personajeService.findById(id);
        model.addAttribute("personaje", p);

        return "personaje-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes redirectAttributes) {
        personajeService.delete(id);
        redirectAttributes.addFlashAttribute("success", "El personaje ha sido eliminado con exito");
        return "redirect:/characters";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable(name = "id") String id, Model model) {
        Personaje personaje = personajeService.findById(id);
        model.addAttribute("p", personaje);
        model.addAttribute("peliculas", personaje.getPeliculas());
        return "personaje-detalle";
    }
}
