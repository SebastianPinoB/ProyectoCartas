package ProyectoCartas.repository;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findByCarta(Carta carta);
}
