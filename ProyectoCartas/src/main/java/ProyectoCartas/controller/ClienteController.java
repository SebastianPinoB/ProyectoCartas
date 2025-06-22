package ProyectoCartas.controller;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Generación de Clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteServ;

    @GetMapping
    @Operation(summary = "Obtener todos los Clientes")
    public Iterable<Cliente> listarClientes(){
        return clienteServ.listarClientes();
    }

    @PostMapping
    @Operation(summary = "Agregar Cliente a Base de datos")
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return clienteServ.guardarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Buscar Cliente por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id){clienteServ.eliminarCliente(id); return ResponseEntity.ok("Cliente eliminado :)");}

}