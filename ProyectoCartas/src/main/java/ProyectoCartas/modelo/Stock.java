package ProyectoCartas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    @SequenceGenerator(
            name = "stock_seq",
            sequenceName = "stock_sequence",
            initialValue = 5
    )
    private Integer idStock;

    @OneToOne
    @JoinColumn(name = "carta_id_fk")
    private Carta carta;

    private int cantidad;
}
