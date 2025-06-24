package ProyectoCartas.controller;

import ProyectoCartas.modelo.Compra;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.service.CompraService;
import ProyectoCartas.assemblers.CompraModelAssembler;

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
@RequestMapping("/api/v2/compras")
@Tag(name = "Compras V2", description = "Generacion de compras")
public class CompraControllerV2 {
    @Autowired
    private  CompraService compraService;
    @Autowired
    private CompraModelAssembler compraModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos las Compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public CollectionModel<EntityModel<Compra>> listaCompras(){
        List<EntityModel<Compra>> compras = compraService.listarCompras().stream().map(compraModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(compras, linkTo(methodOn(CompraControllerV2.class).listaCompras()).withSelfRel());
    }

    @GetMapping(value = "/{idCompra}", produces =  MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener Compra por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public EntityModel<Compra> buscarCompra(@PathVariable Integer idCompra) {
        Compra compra = compraService.findById(idCompra);

        return compraModelAssembler.toModel(compra);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Creacion de una compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<EntityModel<Compra>> adicionaCompra(@RequestBody Compra compra, Cliente cliente){
        Compra compraNueva = compraService.agregarCompra(compra, cliente);

        return ResponseEntity
                .created(linkTo(methodOn(CompraControllerV2.class).buscarCompra(compraNueva.getId())).toUri())
                .body(compraModelAssembler.toModel(compraNueva));
    }

    @DeleteMapping(value = "/{idCompra}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar Compra por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<?> removerCompra(@PathVariable Integer idCompra) {
        compraService.eliminarCompra(idCompra);
        return ResponseEntity.noContent().build();
    }

}
