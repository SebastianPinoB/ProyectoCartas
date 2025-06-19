package ProyectoCartas.service;

<<<<<<< HEAD
import ProyectoCartas.model.Carta;
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
=======
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //Poblar sin hacer Endpoint
    // Se crean tambien stocks para las cartas respectivas
    @PostConstruct
    public void poblarCarta(){
        Carta carta1 = cartaRepository.save(new Carta(null,"Geralt de Rivia ","EXP-01",10000));
        Carta carta2 = cartaRepository.save(new Carta(null,"Pochita Milei","EXP-02",12000));
        Carta carta3 = cartaRepository.save(new Carta(null,"Gordon Freeman","EXP-03",15000));

        stockRepository.save(new Stock(null, carta1, 50));
        stockRepository.save(new Stock(null, carta2, 10));
        stockRepository.save(new Stock(null, carta3, 20));
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
        cartaRepository.deleteById(id);
        stockRepository.deleteById(cartaRepository.findById(id).get().getIdCarta());
>>>>>>> origin/main
    }
}
