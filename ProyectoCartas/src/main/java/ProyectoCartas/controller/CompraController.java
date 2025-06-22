package ProyectoCartas.controller;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compras")
@Tag(name = "Compras", description = "Generación de compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    @Operation(summary = "Obtener todas las boletas")
    public Iterable<Compra> listarCompras() {return compraService.listarCompras(); }

    @PostMapping
    @Operation(summary = "Guardar Boleta")
    public ResponseEntity<?> guardarCompra(@RequestBody Compra compra, Cliente cliente) {
        return compraService.crearCompra(compra, cliente);
    }

    //put?
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Boleta por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCompra(@PathVariable Integer id){compraService.eliminarCompra(id); return ResponseEntity.ok("Compra eliminada :)");}

}
