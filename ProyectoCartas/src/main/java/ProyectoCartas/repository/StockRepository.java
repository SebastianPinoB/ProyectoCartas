package ProyectoCartas.repository;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findByCarta(Carta carta);

    // encontrar la carta por el id de la carta
    Optional<Stock> findByCarta_IdCarta(int idCarta);
}
