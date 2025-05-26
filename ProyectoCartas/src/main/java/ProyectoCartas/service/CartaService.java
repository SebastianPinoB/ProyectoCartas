package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.repository.CartaRepository;
import jakarta.annotation.PostConstruct;
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

    //Poblar sin hacer Endpoint
    @PostConstruct
    public void poblarCarta(){
        this.cartaRepository.save(new Carta(null,"Geralt de Rivia ","EXP-01",10000));
        this.cartaRepository.save(new Carta(null,"Pochita Milei","EXP-02",12000));
        this.cartaRepository.save(new Carta(null,"Gordon Freeman","EXP-03",15000));
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
