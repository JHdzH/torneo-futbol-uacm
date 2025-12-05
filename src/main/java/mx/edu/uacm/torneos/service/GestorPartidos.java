package mx.edu.uacm.torneos.service;

import mx.edu.uacm.torneos.entity.Gol;
import mx.edu.uacm.torneos.entity.Partido;
import mx.edu.uacm.torneos.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GestorPartidos {
    
    @Autowired
    private PartidoRepository partidoRepository;
    
    /**
     * Registrar gol - UC-04
     */
    @Transactional
    public Partido registrarGol(Long partidoId, Gol gol) {
        // Obtener partido
        Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + partidoId));
        
        // Validar estado del partido (debe estar EN_CURSO)
        if (!"EN_CURSO".equals(partido.getEstado())) {
            throw new RuntimeException("El partido no está en curso. Estado actual: " + partido.getEstado());
        }
        
        // Agregar gol al partido
        partido.agregarGol(gol);
        
        // Actualizar marcador (lógica básica)
        // En una implementación real, validarías qué equipo anotó
        
        // Guardar cambios
        return partidoRepository.save(partido);
    }
    
    /**
     * Finalizar partido - UC-04
     */
    @Transactional
    public Partido finalizarPartido(Long partidoId) {
        Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + partidoId));
        
        // Cambiar estado a FINALIZADO
        partido.setEstado("FINALIZADO");
        
        return partidoRepository.save(partido);
    }
    
    /**
     * Obtener partido por ID
     */
    public Partido obtenerPartido(Long partidoId) {
        return partidoRepository.findById(partidoId)
            .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
    }
    
    /**
     * Obtener todos los partidos
     */
    public java.util.List<Partido> obtenerTodosPartidos() {
        return partidoRepository.findAll();
    }
    
    /**
     * Crear nuevo partido
     */
    @Transactional
    public Partido crearPartido(Partido partido) {
        // Validaciones básicas
        if (partido.getEstado() == null) {
            partido.setEstado("PROGRAMADO");
        }
        
        return partidoRepository.save(partido);
    }
}