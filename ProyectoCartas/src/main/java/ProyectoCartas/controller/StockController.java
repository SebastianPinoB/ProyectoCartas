package ProyectoCartas.controller;

import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// solo se hace la consulta debido a que stock se creara con la carta
@RestController
@RequestMapping("/api/v1/stockCartas")
@Tag(name = "Stock", description = "Administración de inventario")
public class StockController {
    @Autowired
    private StockService stockServ;

    @GetMapping
    @Operation(summary = "Obtener stock")
    public Iterable<Stock> listarStock(){
        return stockServ.listarStock();
    }
}
