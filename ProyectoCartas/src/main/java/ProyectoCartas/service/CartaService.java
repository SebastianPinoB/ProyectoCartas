package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.repository.CartaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {
    private CartaRepository cartaRepository;

    public List<Carta> listarCartas(){
        return cartaRepository.findAll();
    }

    public Carta findById(Integer id){
        return cartaRepository.findById(id);
    }

    public List<Carta> findByCodigo(String codigo){
        return cartaRepository.findByCodigo(codigo);
    }

    public Carta guardarCarta(Carta carta){
        return cartaRepository.guardarCarta(carta);
    }

}
