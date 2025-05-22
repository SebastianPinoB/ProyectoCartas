package ProyectoCartas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false, unique = true)
    private String runCliente;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String apellidoCliente;

    @ManyToOne
    @JoinColumn(name = "idCarta")
    private Carta cartasCompradas;

    /*
    @ManyToOne
    @JoinColumn

     */
}
