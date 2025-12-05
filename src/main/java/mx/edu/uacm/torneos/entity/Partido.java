package mx.edu.uacm.torneos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidos")
public class Partido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "estado", length = 20)
    private String estado; // PROGRAMADO, EN_CURSO, FINALIZADO
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @Column(name = "goles_local")
    private Integer golesLocal = 0;
    
    @Column(name = "goles_visitante")
    private Integer golesVisitante = 0;
    
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Gol> goles = new ArrayList<>();
    
    // Constructores
    public Partido() {}
    
    public Partido(String estado, LocalDateTime fecha) {
        this.estado = estado;
        this.fecha = fecha;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public Integer getGolesLocal() { return golesLocal; }
    public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }
    
    public Integer getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }
    
    public List<Gol> getGoles() { return goles; }
    public void setGoles(List<Gol> goles) { this.goles = goles; }
    
    // Método para agregar gol
    public void agregarGol(Gol gol) {
        gol.setPartido(this);
        this.goles.add(gol);
    }
}