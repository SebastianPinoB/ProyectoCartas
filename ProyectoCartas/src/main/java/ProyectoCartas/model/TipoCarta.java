package ProyectoCartas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_carta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class TipoCarta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoCarta;

    @Column(nullable = false)
    private String nombreTipoCarta;
}
