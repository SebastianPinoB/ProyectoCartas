package ProyectoCartas.repository;

import ProyectoCartas.model.Rareza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RarezaRepository extends JpaRepository<Rareza, Integer> {
}
