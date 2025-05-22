package ProyectoCartas.controller;

import ProyectoCartas.model.Carta;
import ProyectoCartas.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


    // esta wea es la menos practica que he hecho.
    @PostMapping
    public Carta guardarCarta(@RequestBody Carta cartaSave){
        return cartaServ.guardarCarta(cartaSave);
    }
}
