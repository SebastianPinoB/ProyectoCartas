package ProyectoCartas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_carta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class TipoCarta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoCarta;

    @Column(nullable = false)
    private String nombreTipoCarta;

    public Integer getIdTipoCarta() {
        return idTipoCarta;
    }

    public void setIdTipoCarta(Integer idTipoCarta) {
        this.idTipoCarta = idTipoCarta;
    }

    public String getNombreTipoCarta() {
        return nombreTipoCarta;
    }

    public void setNombreTipoCarta(String nombreTipoCarta) {
        this.nombreTipoCarta = nombreTipoCarta;
    }
}
