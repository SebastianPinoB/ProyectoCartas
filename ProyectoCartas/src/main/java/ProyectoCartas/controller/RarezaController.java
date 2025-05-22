package ProyectoCartas.controller;

import ProyectoCartas.model.Rareza;
import ProyectoCartas.service.RarezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rarezas")
public class RarezaController {
    @Autowired
    private RarezaService rarezaServ;

    @GetMapping
    public List<Rareza> listarRarezas(){
        return rarezaServ.listar();
    }

    @PostMapping
    public Rareza crearRareza(@RequestBody Rareza rareza){
        return rarezaServ.crearRareza(rareza);
    }
}
