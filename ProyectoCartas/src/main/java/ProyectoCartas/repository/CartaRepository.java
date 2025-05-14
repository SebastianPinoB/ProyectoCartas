package ProyectoCartas.repository;

import ProyectoCartas.modelo.Carta;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Integer>{

    Carta findByIdCarta(Integer id);
}
