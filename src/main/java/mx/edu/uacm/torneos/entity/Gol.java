package mx.edu.uacm.torneos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goles")
public class Gol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "minuto")
    private Integer minuto;
    
    @Column(name = "es_penal")
    private Boolean esPenal = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partido_id")
    private Partido partido;
    
    // Constructores
    public Gol() {}
    
    public Gol(Integer minuto, Boolean esPenal) {
        this.minuto = minuto;
        this.esPenal = esPenal;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getMinuto() { return minuto; }
    public void setMinuto(Integer minuto) { this.minuto = minuto; }
    
    public Boolean getEsPenal() { return esPenal; }
    public void setEsPenal(Boolean esPenal) { this.esPenal = esPenal; }
    
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    
    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
}