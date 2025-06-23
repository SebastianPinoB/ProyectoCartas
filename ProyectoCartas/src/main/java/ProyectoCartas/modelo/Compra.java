package ProyectoCartas.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Compra")
@Data
@AllArgsConstructor
@NoArgsConstructor

// Secuencia que empieza de 10 hacia adelante
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(
            name = "compra_seq",
            sequenceName = "compra_sequence",
            initialValue = 10
    )
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "carta_id_fk")
    private Carta carta;
    @ManyToOne
    @JoinColumn(name = "cliente_id_fk")
    private Cliente cliente;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha = LocalDate.now();


}
