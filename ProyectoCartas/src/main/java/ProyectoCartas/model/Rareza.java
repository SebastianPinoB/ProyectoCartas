package ProyectoCartas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rareza")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Rareza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRareza;

    @Column(nullable = false)
    private String nombreRareza;
}
