package ProyectoCartas.repository;


import ProyectoCartas.modelo.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
    Boleta findBoletaById(Integer id);
}
