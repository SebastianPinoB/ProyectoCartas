package ProyectoCartas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "carta")
@Getter
@Setter
public class Carta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombreCarta;
    private String rarezaCarta;
    private Integer precioCarta;


}
