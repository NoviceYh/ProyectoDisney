
package Disney.demo.controladores;

import Disney.demo.entidades.Pelicula;
import Disney.demo.entidades.Personaje;
import Disney.demo.errores.ErrorService;
import Disney.demo.servicios.PeliculaService;
import Disney.demo.servicios.PersonajeService;
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
    
    @Autowired
    private PeliculaService peliculaService;
    
    @Autowired
    private PersonajeService personajeService;
    
    @GetMapping("")
    public String list(Model model, @RequestParam(required = false) String q) {
        if (q == null || q.length() == 0) {
            model.addAttribute("peliculas", peliculaService.findAll());
        } else {
            model.addAttribute("peliculas", peliculaService.findByTitle(q));
        }
        return "pelicula-list";
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
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("personajes", personajeService.findAll());
//        }
        return "pelicula-form";
    }
    
    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Pelicula pelicula,
            RedirectAttributes redirectAttributes, @RequestParam(name = "foto") MultipartFile foto) {
        
        System.out.println("paso por aqui");
        List<Personaje> p = pelicula.getPersonajes();
        for (Personaje personaje : p) {
            System.out.println("personaje: "+personaje.getNombre());
            System.out.println("id: "+personaje.getId());
        }
        
        byte[] contenido = null;

        //Verificamos que el archivo no este vacio
        if (!foto.isEmpty()) {
            //Verficiamos que el contenido del archivo sea una foto tipo jpg o png
            if (foto.getContentType().endsWith("jpeg") || foto.getContentType().endsWith("png") || foto.getContentType().endsWith("svg")) {
                try {
                    contenido = foto.getBytes();
                    pelicula.setImagen(contenido);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            peliculaService.save(pelicula);
            redirectAttributes.addFlashAttribute("success", "La pelicula se ha guardado con exito!");
            return "redirect:/movies";
        } catch (ErrorService ex) {
            model.addAttribute("error", ex.getMessage());
            return "pelicula-form";
        }
    }
    
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> fotoPublicacion(@PathVariable(value = "id") String id) {
        Pelicula p = peliculaService.findById(id);

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
        Pelicula p = peliculaService.findById(id);
        model.addAttribute("pelicula", p);
        model.addAttribute("personajes", personajeService.findAll());
        
        return "pelicula-form";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes redirectAttributes) {
        peliculaService.delete(id);
        redirectAttributes.addFlashAttribute("success", "La pelicula ha sido eliminado con exito");
        return "redirect:/movies";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable(name = "id") String id, Model model) {
        Pelicula pelicula = peliculaService.findById(id);
        model.addAttribute("p", pelicula);
        model.addAttribute("personajes", pelicula.getPersonajes());
//        model.addAttribute("peliculas", personaje.getPelicula());
        return "pelicula-detalle";
    }
}
