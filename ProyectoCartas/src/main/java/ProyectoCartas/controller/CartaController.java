package ProyectoCartas.controller;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.service.CartaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/v1/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @GetMapping
    public List<Carta> listarCartas(){
        List<Carta> cartas = cartaService.listarCartas();
        return cartas;
    }

    @PostMapping
    public ResponseEntity<Carta> agregarCarta(@RequestBody Carta carta){
        Carta cartaNueva = cartaService.guardarCarta(carta);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaNueva);
    }
}
