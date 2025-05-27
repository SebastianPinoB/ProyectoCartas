package ProyectoCartas.controller;

import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// solo se hace la consulta debido a que stock se creara con la carta
@RestController
@RequestMapping("/api/v1/stockCartas")
public class StockController {
    @Autowired
    private StockService stockServ;

    @GetMapping
    public Iterable<Stock> listarStock(){
        return stockServ.listarStock();
    }
}
