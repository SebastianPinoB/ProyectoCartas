package ProyectoCartas.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarta;

    @Column(nullable = false)
    private String nombreCarta;

    @Column(nullable = false)
    private int precioCarta;

    @Column(nullable = false)
    private int stockCarta;

    @Column(nullable= false)
    private String rareza;

    @Column(nullable = false)
    private String tipoCarta;
}
