package ProyectoCartas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compra")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

// Aqui va todo lo que es la logica de negocio
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;
}
