package ProyectoCartas.controller;

import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public Iterable<Compra> listarCompras(){
        return compraService.listarCompras();
    }

    @PostMapping
    public Compra guardarCompra(@RequestBody Compra compra){
        return compraService.guardarCompra(compra);
    }

    // falta el put y delete
}
