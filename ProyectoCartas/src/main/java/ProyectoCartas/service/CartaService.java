package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CartaService {
    private final CartaRepository cartaRepository;

    @Autowired
    private StockRepository stockRepository;

    public CartaService(CartaRepository cartaRepository){
        this.cartaRepository = cartaRepository;
    }

    public List<Carta> listarCartas(){

        return cartaRepository.findAll();
    }

    public Carta findById(Integer id){
        return cartaRepository.findById(id).get();
    }

    // Este se utiliza en el POST, el metodo para agregar expecificamente
    public Carta agregarCarta(Carta carta, int cantidad){
        Carta cartaG = cartaRepository.save(carta);
        // al crearse la carta se crea automaticamente un stock con la cantidad
        Stock stockG = stockRepository.save(new Stock(null, cartaG, cantidad));
        return cartaG;
    }

    // Este se utiliza para el guardar, cielos.
    public Carta guardarCarta(Carta carta){
        return cartaRepository.save(carta);
    }

    // Se elimina tambien el stock
    public void eliminarCarta(Integer id){
        Carta cartaEliminar = cartaRepository.findById(id).orElseThrow(null);
        Stock stockEliminar = stockRepository.findByCarta(cartaEliminar);

        if (stockEliminar != null){
            stockRepository.delete(stockEliminar);
        }

        cartaRepository.delete(cartaEliminar);
    }

    public List<Carta> filtrarCartasMenor_a_Mayor(){
        List<Carta> cartas = listarCartas();
        cartas.sort(Comparator.comparing(Carta::getPrecio));
        return cartas;
    }

    public List<Carta> filtrarCartasMayor_a_Menor(){
        List<Carta> cartas = listarCartas();
        cartas.sort(Comparator.comparing(Carta::getPrecio).reversed());
        return cartas;
    }
}
