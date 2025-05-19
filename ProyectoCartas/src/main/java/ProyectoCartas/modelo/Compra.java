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
    private Integer idCompra;


    /*
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente Cliente;
    */

    @ManyToOne
    @JoinColumn(name = "idCarta", nullable = false)
    private Carta Carta;

    @Column(nullable = false)
    private String fecha;
}
