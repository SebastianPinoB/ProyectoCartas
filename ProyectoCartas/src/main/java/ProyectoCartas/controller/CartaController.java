package ProyectoCartas.controller;

import ProyectoCartas.model.Carta;
import ProyectoCartas.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cartas")
public class CartaController {
    @Autowired
    private CartaService cartaServ;

    @GetMapping
    public List<Carta> listarCartas(){
        return cartaServ.listar();
    }


    // pendiente no funciona
    @PostMapping("/crear")
    public Carta guardarCarta(@RequestBody Carta cartaSave, @RequestParam Integer idTipoCarta, @RequestParam Integer idRareza){
        return cartaServ.guardarCarta(cartaSave);
    }
}
