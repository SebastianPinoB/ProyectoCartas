package ProyectoCartas.repository;

import ProyectoCartas.model.TipoCarta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCartaRepository extends JpaRepository<TipoCarta, Integer> {
}
