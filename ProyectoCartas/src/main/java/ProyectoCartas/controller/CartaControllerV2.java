package ProyectoCartas.controller;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.service.CartaService;
import ProyectoCartas.assemblers.CartaModelAssembler;

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
public class CartaControllerV2 {
    @Autowired
    private CartaService cartaService;
    @Autowired
    private CartaModelAssembler cartaModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Carta>> listaCartas() {
        List<EntityModel<Carta>> cartas = cartaService.listarCartas().stream().map(cartaModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(cartas, linkTo(methodOn(CartaControllerV2.class).listaCartas()).withSelfRel());
    }

    @GetMapping(value = "/{idCarta}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Carta> buscarCartaPorID(@PathVariable Integer idCarta) {
        Carta carta = cartaService.findById(idCarta);
        return cartaModelAssembler.toModel(carta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carta>> crearCarta(@RequestBody Carta carta) {
        Carta cartaNueva = cartaService.guardarCarta(carta);

        return ResponseEntity
                .created(linkTo(methodOn(CartaControllerV2.class).buscarCartaPorID(cartaNueva.getIdCarta())).toUri())
                .body(cartaModelAssembler.toModel(cartaNueva));
    }

    @PutMapping(value = "/{idCarta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carta>> actualizarCarta(@PathVariable Integer idCarta, @RequestBody Carta carta) {
        carta.setIdCarta(idCarta);
        Carta actualizarCarta = cartaService.guardarCarta(carta);

        return ResponseEntity.ok(cartaModelAssembler.toModel(actualizarCarta));
    }

    @DeleteMapping(value = "/{idCarta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminarCarta(@PathVariable Integer idCarta) {
        cartaService.eliminarCarta(idCarta);
        return ResponseEntity.notFound().build();
    }
}
