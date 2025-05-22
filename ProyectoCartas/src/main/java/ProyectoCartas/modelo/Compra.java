package ProyectoCartas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Compra")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente Cliente;
    */

    @ManyToOne
    @JoinColumn(name = "idCarta")
    private Carta Carta;

    /*
    @Column(nullable = false)
    private String fecha;
     */
}
