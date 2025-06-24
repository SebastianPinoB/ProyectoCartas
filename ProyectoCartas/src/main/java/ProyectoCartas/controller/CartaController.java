package ProyectoCartas.controller;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.CartaService;
import ProyectoCartas.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cartas")
@Tag(name = "Cartas", description = "Generación de Cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    // se agrego el stock service
    @Autowired
    private StockService stockService;


    @GetMapping
    @Operation(summary = "Obtener las Cartas generadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public List<Carta> listarCartas(){
        return cartaService.listarCartas();
    }

    // aqui simplemente tienes que poner la cantidad con la url "localhost:8080/cartas/{cantidad}" en el postman
    @PostMapping("/{cantidad}")
    @Operation(summary = "Agregar Cartas a la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Carta no encontrada")
    })
    public ResponseEntity<Carta> agregarCarta(@RequestBody Carta carta, @PathVariable Integer cantidad){
        return new ResponseEntity<>(cartaService.agregarCarta(carta, cantidad), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Carta por ID")
    public Carta buscarCarta(@PathVariable Integer id){
        return cartaService.findById(id);
    }


    // No se considera el id, debido a que se crea con la compra.
    @PutMapping("/{id}/{cantidad}") // imagina que tenga que hacer esto desde la url :skull:
    @Operation(summary = "Editar Carta de la ID, cambiando la cantidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Carta no encontrada")
    })
    public Carta cambiarCarta(@PathVariable Integer id, @RequestBody Carta c, @PathVariable int cantidad) {
        Carta cartaR = cartaService.findById(id);
        cartaR.setNombre(c.getNombre());
        cartaR.setCodigoExp(c.getCodigoExp());
        cartaR.setPrecio(c.getPrecio());

        Stock stock = stockService.findByCarta(cartaR);
        if (stock == null){
            stock = new Stock(null, cartaR, cantidad);
        } else {
            stock.setCantidad(stock.getCantidad() + cantidad);
        }

        stockService.guardarStock(stock);
        return cartaService.guardarCarta(cartaR);
    }


    @GetMapping("/filtroMenor")
    @Operation(summary = "Filtrado de cartas en base a precio menor a mayor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public List<Carta> filtrarCartasMenor(){
        return cartaService.filtrarCartasMenor_a_Mayor();
    }

    @GetMapping("/filtroMayor")
    @Operation(summary = "Filtrado de cartas en base a precio mayor a menor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public List<Carta> filtrarCartasMayor(){
        return cartaService.filtrarCartasMayor_a_Menor();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Carta segun ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Carta no encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCarta(@PathVariable Integer id){
        cartaService.eliminarCarta(id);
    }
}
