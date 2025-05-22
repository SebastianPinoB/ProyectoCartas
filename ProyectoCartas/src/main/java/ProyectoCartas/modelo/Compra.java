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

public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int id_carta;
    //private int id_cliente;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha = LocalDate.now();

    /*
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente Cliente;
    */

    /*
    @Column(nullable = false)
    private String fecha;
     */
}
