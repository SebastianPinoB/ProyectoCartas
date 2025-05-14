package ProyectoCartas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "Carta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarta;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String codigoExp;
    @Column(nullable = false)
    private int precio;


}
