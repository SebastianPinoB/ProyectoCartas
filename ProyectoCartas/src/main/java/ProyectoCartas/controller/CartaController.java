package ProyectoCartas.controller;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.CartaService;
import ProyectoCartas.service.StockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/v1/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    // se agrego el stock service
    @Autowired
    private StockService stockService;


    @GetMapping
    public List<Carta> listarCartas(){
        return cartaService.listarCartas();
    }

    // aqui simplemente tienes que poner la cantidad con la url "localhost:8080/cartas/{cantidad}" en el postman
    @PostMapping("/{cantidad}")
    public ResponseEntity<Carta> agregarCarta(@RequestBody Carta carta, @PathVariable Integer cantidad){
        return new ResponseEntity<>(cartaService.agregarCarta(carta, cantidad), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Carta buscarCarta(@PathVariable Integer id){
        return cartaService.findById(id);
    }


    // No se considera el id, debido a que se crea con la compra.
    @PutMapping("/{id}/{cantidad}") // imagina que tenga que hacer esto desde la url :skull:
    public Carta cambiarCarta(@PathVariable Integer id, @RequestBody Carta c, @PathVariable int cantidad) {
        Carta cartaR = cartaService.findById(id);
        cartaR.setNombre(c.getNombre());
        cartaR.setCodigoExp(c.getCodigoExp());
        cartaR.setPrecio(c.getPrecio());

        Stock stock = stockService.findByCarta(cartaR);
        if (stock == null){
            stock = new Stock(null, cartaR, cantidad);
        } else {
            stock.setCantidad(stock.getCantidad() + cantidad);
        }

        stockService.guardarStock(stock);
        return cartaService.guardarCarta(cartaR);
    }


    @GetMapping("/filtroMenor")
    public List<Carta> filtrarCartasMenor(){
        List<Carta> cartas = cartaService.listarCartas();
        cartas.sort(Comparator.comparing(Carta::getPrecio));
        return cartas;
    }

    @GetMapping("/filtroMayor")
    public List<Carta> filtrarCartasMayor(){
        List<Carta> cartas = cartaService.listarCartas();
        cartas.sort(Comparator.comparing(Carta::getPrecio).reversed());
        return cartas;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCarta(@PathVariable Integer id){
        cartaService.eliminarCarta(id);
    }
}