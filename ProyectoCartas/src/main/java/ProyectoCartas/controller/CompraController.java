package ProyectoCartas.controller;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public Iterable<Compra> listarCompras() {return compraService.listarCompras(); }

    @PostMapping
    public ResponseEntity<?> guardarCompra(@RequestBody Compra compra, Cliente cliente){
        return compraService.crearCompra(compra, cliente);
    }

    //put?
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCompra(@PathVariable Integer id){compraService.eliminarCompra(id); return ResponseEntity.ok("Compra eliminada :)");}

}
