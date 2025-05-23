package ProyectoCartas.model;


import jakarta.persistence.*;


@Entity
@Table (name = "carta")
public class Carta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombreCarta;
    private String rarezaCarta;
    private Integer precioCarta;


}
