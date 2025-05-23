package ProyectoCartas.repository;

import ProyectoCartas.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaRepository extends JpaRepository<Carta, Integer> { }
