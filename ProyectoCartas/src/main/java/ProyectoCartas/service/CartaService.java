package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.repository.CartaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartaService {
    private final CartaRepository cartaRepository;

    public CartaService(CartaRepository cartaRepository){
        this.cartaRepository = cartaRepository;
    }

    public List<Carta> listarCartas(){

        return cartaRepository.findAll();
    }

    public Carta findById(Integer id){
        return cartaRepository.findById(id).get();
    }

    public Carta guardarCarta(Carta carta){

        return cartaRepository.save(carta);
    }

    public void eliminarCarta(Integer id){
        cartaRepository.deleteById(id);
    }
}
