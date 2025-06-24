package ProyectoCartas.controller;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compras")
@Tag(name = "Compras", description = "Generación de Compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    @Operation(summary = "Obtener todas las compras realizadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public Iterable<Compra> listarCompras() {return compraService.listarCompras(); }

    @GetMapping("/{idCompra}")
    @Operation(summary = "Obtener una compra por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public Compra buscarCompra(@PathVariable Integer idCompra) {
        return compraService.findById(idCompra);
    }

    @PostMapping
    @Operation(summary = "Creacion de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<?> guardarCompra(@RequestBody Compra compra, Cliente cliente) {
        return compraService.crearCompra(compra, cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar la compra por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCompra(@PathVariable Integer id){compraService.eliminarCompra(id); return ResponseEntity.ok("Compra eliminada :)");}

}
