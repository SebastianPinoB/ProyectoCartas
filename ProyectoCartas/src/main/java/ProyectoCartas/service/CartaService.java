package ProyectoCartas.service;

import ProyectoCartas.model.Carta;
import ProyectoCartas.repository.CartaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CartaService {

    private final CartaRepository repo;
    public CartaService (CartaRepository repo) {
        this.repo = repo;
    }
    public Carta guardar(Carta c) {
        return repo.save(c);
    }

    public List<Carta> listar() {
        return repo.findAll();
    }

    public Carta obtener(Integer id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carta " + id + " no encontrada" ));
    }
    public void eliminar (Integer id){
        repo.deleteById(id);
    }
}
