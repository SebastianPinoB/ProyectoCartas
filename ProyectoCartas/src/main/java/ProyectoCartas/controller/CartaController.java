package ProyectoCartas.controller;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.service.CartaService;
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

    @GetMapping
    public List<Carta> listarCartas(){
        return cartaService.listarCartas();
    }

    @PostMapping
    public ResponseEntity<Carta> agregarCarta(@RequestBody Carta carta){
        return new ResponseEntity<>(cartaService.guardarCarta(carta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Carta buscarCarta(@PathVariable Integer id){
        return cartaService.findById(id);
    }

    @PutMapping("/{id}")
    public Carta cambiarCarta(@PathVariable Integer id, @RequestBody Carta c) {
        Carta cartaR = cartaService.findById(id);
        cartaR.setNombre(c.getNombre());
        cartaR.setCodigoExp(c.getCodigoExp());
        cartaR.setPrecio(c.getPrecio());
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
