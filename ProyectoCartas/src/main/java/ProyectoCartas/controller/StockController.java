package ProyectoCartas.controller;


import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// solo se hace la consulta debido a que stock se creara con la carta
@RestController
@RequestMapping("/api/v1/stockCartas")
@Tag(name = "Stock", description = "Generacion de Stock de las cartas")
public class StockController {
    @Autowired
    private StockService stockServ;

    @GetMapping
    @Operation(summary = "Obtener los stock de las cartas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public Iterable<Stock> listarStock(){
        return stockServ.listarStock();
    }

    // busca la carta a traves de su id en el stock
    @GetMapping("/carta/{idCarta}")
    @Operation(summary = "Obtener la compra por id mediante el stock de la carta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<Stock> buscarCartaPorID(@PathVariable Integer idCarta){
        Stock cartaEncontrar = stockServ.findByCartaID(idCarta);

        if (cartaEncontrar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartaEncontrar);

    }
}
