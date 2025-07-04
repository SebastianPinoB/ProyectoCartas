package ProyectoCartas.controller;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.service.ClienteService;
import ProyectoCartas.assemblers.ClienteModelAssembler;

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
@RequestMapping("/api/v2/clientes")
@Tag(name = "Clientes V2", description = "Generacion de clientes")
public class ClienteControllerV2 {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public CollectionModel<EntityModel<Cliente>> listarClientes() {
        List<EntityModel<Cliente>> clientes = clienteService.listarClientes().stream().map(clienteModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(clientes, linkTo(methodOn(ClienteControllerV2.class).listarClientes()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar Cliente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public EntityModel<Cliente> buscarClientePorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.encontrarClientePorId(id);
        return  clienteModelAssembler.toModel(cliente);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Creacion de cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<EntityModel<Cliente>> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).buscarClientePorId(nuevoCliente.getIdCliente())).toUri())
                .body(clienteModelAssembler.toModel(nuevoCliente));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizacion de datos del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<EntityModel<Cliente>> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        Cliente actualizarCliente = clienteService.guardarCliente(cliente);

        return ResponseEntity
                .ok(clienteModelAssembler.toModel(actualizarCliente));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminacion de un Cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
