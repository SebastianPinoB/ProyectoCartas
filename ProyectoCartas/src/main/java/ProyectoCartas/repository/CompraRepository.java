package ProyectoCartas.repository;

import ProyectoCartas.modelo.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    //List<Compra> findAllById(Integer idCliente);

}
