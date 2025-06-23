package ProyectoCartas.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boleta")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boleta_seq")
    @SequenceGenerator(
            name = "boleta_seq",
            sequenceName = "boleta_sequence",
            initialValue = 100
    )
    private Integer id;

    @OneToOne
    @JoinColumn(name = "compra_id_fk")
    private Compra compra;

}
