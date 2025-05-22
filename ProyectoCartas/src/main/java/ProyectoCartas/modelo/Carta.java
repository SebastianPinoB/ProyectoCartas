package ProyectoCartas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "Carta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarta;
    private String nombre;
    private String codigoExp;
    private int precio;


    public Integer getIdCarta() {
        return idCarta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoExp() {
        return codigoExp;
    }

    public int getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigoExp(String codigoExp) {
        this.codigoExp = codigoExp;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
