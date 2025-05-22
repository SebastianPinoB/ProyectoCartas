package ProyectoCartas.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarta;

    @Column(nullable = false)
    private String nombreCarta;

    @Column(nullable = false)
    private int precioCarta;

    @Column(nullable = false)
    private int stockCarta;

    // muchas cartas pueden tener un tipo de carta
    @ManyToOne
    @JoinColumn(name = "idTipoCarta")
    private TipoCarta tipoDeCarta;

    // muchas cartas tienen un tipo de rareza
    @ManyToOne
    @JoinColumn(name = "idRareza")
    private Rareza rarezaCarta;

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public Integer getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(Integer idCarta) {
        this.idCarta = idCarta;
    }

    public int getPrecioCarta() {
        return precioCarta;
    }

    public void setPrecioCarta(int precioCarta) {
        this.precioCarta = precioCarta;
    }

    public int getStockCarta() {
        return stockCarta;
    }

    public void setStockCarta(int stockCarta) {
        this.stockCarta = stockCarta;
    }

    public Rareza getRarezaCarta() {
        return rarezaCarta;
    }

    public void setRarezaCarta(Rareza rarezaCarta) {
        this.rarezaCarta = rarezaCarta;
    }

    public TipoCarta getTipoDeCarta() {
        return tipoDeCarta;
    }

    public void setTipoDeCarta(TipoCarta tipoDeCarta) {
        this.tipoDeCarta = tipoDeCarta;
    }
}
