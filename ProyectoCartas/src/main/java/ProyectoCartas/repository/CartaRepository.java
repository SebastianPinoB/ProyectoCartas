package ProyectoCartas.repository;

import ProyectoCartas.modelo.Carta;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long>{

    //Lista Cartas
    public List<Carta> findAll();

    //Buscar Clientes por id
    Carta findById(Integer id);

    //Buscar Clientes por codigoExp
    List<Carta> findByCodigo(String codigo);

    //Guardar carta
    Carta guardarCarta(Carta carta);

}
