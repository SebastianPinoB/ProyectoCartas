package ProyectoCartas.controller;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteServ;

    @GetMapping
    public Iterable<Cliente> listarClientes(){
        return clienteServ.listarClientes();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return clienteServ.guardarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id){clienteServ.eliminarCliente(id); return ResponseEntity.ok("Cliente eliminado :)");}

}