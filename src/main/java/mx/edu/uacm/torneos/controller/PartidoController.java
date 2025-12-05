package mx.edu.uacm.torneos.controller;

import mx.edu.uacm.torneos.entity.Partido;
import mx.edu.uacm.torneos.service.GestorPartidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/partidos")
public class PartidoController {
    
    @Autowired
    private GestorPartidos gestorPartidos;
    
    /**
     * Listar todos los partidos
     */
    @GetMapping("/")
    public String listarPartidos(Model model) {
        model.addAttribute("partidos", gestorPartidos.obtenerTodosPartidos());
        return "partidos/lista";
    }
    
    /**
     * Ver detalles de un partido
     */
    @GetMapping("/{id}")
    public String verPartido(@PathVariable Long id, Model model) {
        Partido partido = gestorPartidos.obtenerPartido(id);
        model.addAttribute("partido", partido);
        return "partidos/detalle";
    }
    
    /**
     * Formulario para crear nuevo partido
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("partido", new Partido());
        return "partidos/formulario";
    }
    
    /**
     * Crear nuevo partido
     */
    @PostMapping("/crear")
    public String crearPartido(@ModelAttribute Partido partido) {
        if (partido.getFecha() == null) {
            partido.setFecha(LocalDateTime.now());
        }
        
        gestorPartidos.crearPartido(partido);
        return "redirect:/partidos/";
    }
    
    /**
     * Finalizar partido
     */
    @PostMapping("/{id}/finalizar")
    public String finalizarPartido(@PathVariable Long id) {
        gestorPartidos.finalizarPartido(id);
        return "redirect:/partidos/" + id;
    }
}