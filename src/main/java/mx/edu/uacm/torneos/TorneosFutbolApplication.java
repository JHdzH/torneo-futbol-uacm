package mx.edu.uacm.torneos;

import mx.edu.uacm.torneos.entity.Equipo;
import mx.edu.uacm.torneos.entity.Partido;
import mx.edu.uacm.torneos.repository.EquipoRepository;
import mx.edu.uacm.torneos.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TorneosFutbolApplication implements CommandLineRunner {
    
    @Autowired
    private PartidoRepository partidoRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(TorneosFutbolApplication.class, args);
        System.out.println("==========================================");
        System.out.println("SISTEMA DE TORNEOS DE FUTBOL - UC-04");
        System.out.println("Base de datos: PostgreSQL - torneofb");
        System.out.println("URL: http://localhost:8080/torneos");
        System.out.println("==========================================");
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Crear datos de prueba si no existen
        if (partidoRepository.count() == 0) {
            System.out.println("Creando datos de prueba...");
            
            // Crear equipos
            Equipo equipoA = new Equipo("Equipo A");
            Equipo equipoB = new Equipo("Equipo B");
            
            equipoRepository.save(equipoA);
            equipoRepository.save(equipoB);
            
            // Crear partidos de prueba
            Partido partido1 = new Partido("EN_CURSO", LocalDateTime.now());
            partido1.setGolesLocal(1);
            partido1.setGolesVisitante(0);
            
            Partido partido2 = new Partido("PROGRAMADO", LocalDateTime.now().plusDays(1));
            
            partidoRepository.save(partido1);
            partidoRepository.save(partido2);
            
            System.out.println("Datos de prueba creados exitosamente");
            System.out.println("- 2 equipos creados");
            System.out.println("- 2 partidos creados");
        }
    }
}