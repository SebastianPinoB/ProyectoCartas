package ProyectoCartas.controller;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.service.CartaService;
import ProyectoCartas.service.StockService;
import ProyectoCartas.assemblers.CartaModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/cartas")
@Tag(name = "Cartas V2", description = "Generacion de Cartas")
public class CartaControllerV2 {
    @Autowired
    private CartaService cartaService;
    @Autowired
    private CartaModelAssembler cartaModelAssembler;
    @Autowired
    private StockService stockService;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos las Cartas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public CollectionModel<EntityModel<Carta>> listaCartas() {
        List<EntityModel<Carta>> cartas = cartaService.listarCartas().stream().map(cartaModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(cartas, linkTo(methodOn(CartaControllerV2.class).listaCartas()).withSelfRel());
    }

    @GetMapping(value = "/{idCarta}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener Carta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public EntityModel<Carta> buscarCartaPorID(@PathVariable Integer idCarta) {
        Carta carta = cartaService.findById(idCarta);
        return cartaModelAssembler.toModel(carta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Creacion de una Carta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<EntityModel<Carta>> crearCarta(@RequestBody Carta carta, @PathVariable int cantidad) {
        Carta cartaNueva = cartaService.agregarCarta(carta, cantidad);

        return ResponseEntity
                .created(linkTo(methodOn(CartaControllerV2.class).buscarCartaPorID(cartaNueva.getIdCarta())).toUri())
                .body(cartaModelAssembler.toModel(cartaNueva));
    }

    @PutMapping(value = "/{idCarta}/{cantidad}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Modificar los datos de la carta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<EntityModel<Carta>> actualizarCarta(@PathVariable Integer idCarta, @RequestBody Carta carta, @PathVariable int cantidad) {
        Carta cartaR = cartaService.findById(idCarta);

        if (cartaR == null) {
            return ResponseEntity.notFound().build();
        }

        cartaR.setNombre(carta.getNombre());
        cartaR.setCodigoExp(carta.getCodigoExp());
        cartaR.setPrecio(carta.getPrecio());

        Carta cartaGuardada = cartaService.guardarCarta(cartaR);
        Stock stock = stockService.findByCarta(cartaR);

        if (stock == null){
            stock = new Stock(null, cartaR, cantidad);

        } else {
            stock.setCantidad(stock.getCantidad() + cantidad);
        }

        stockService.guardarStock(stock);
        return ResponseEntity.ok(cartaModelAssembler.toModel(cartaGuardada));
    }

    @DeleteMapping(value = "/{idCarta}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar la Carta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<?> eliminarCarta(@PathVariable Integer idCarta) {
        cartaService.eliminarCarta(idCarta);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtroMenor")
    @Operation(summary = "Filtrar Cartas por precio de menor a mayor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public CollectionModel<EntityModel<Carta>> filtrarCartasMenor(){
        List<Carta> cartasOrdenadas = cartaService.filtrarCartasMenor_a_Mayor();
        List<EntityModel<Carta>> cartaModel = cartasOrdenadas.stream().map(cartaModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(cartaModel,
                linkTo(methodOn(CartaControllerV2.class).filtrarCartasMenor()).withSelfRel());
    }

    @GetMapping("/filtroMayor")
    @Operation(summary = "Filtrar Cartas por precio de mayor a menor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public CollectionModel<EntityModel<Carta>> filtrarCartasMayor(){
        List<Carta> cartasOrdenadas = cartaService.filtrarCartasMayor_a_Menor();
        List<EntityModel<Carta>> cartaModel = cartasOrdenadas.stream().map(cartaModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(cartaModel,
                linkTo(methodOn(CartaControllerV2.class).filtrarCartasMenor()).withSelfRel());
    }
}
