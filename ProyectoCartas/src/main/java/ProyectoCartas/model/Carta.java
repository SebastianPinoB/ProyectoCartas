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

    // muchas cartas pueden tener un tipo de carta
    @ManyToOne
    @JoinColumn(name = "idTipoCarta")
    private TipoCarta tipoDeCarta;

    // muchas cartas tienen un tipo de rareza
    @ManyToOne
    @JoinColumn(name = "idRareza")
    private Rareza rarezaCarta;
}
