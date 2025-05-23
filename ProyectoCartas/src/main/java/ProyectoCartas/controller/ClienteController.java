package ProyectoCartas.controller;

import ProyectoCartas.model.Cliente;
import ProyectoCartas.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service){ this.service = service; }
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente nuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }
    @GetMapping
    public List<Cliente> listar(){ return service.listar(); }
    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Integer id){ return service.obtener(id); }
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id,@RequestBody Cliente datos){
        Cliente e = service.obtener(id);
        return service.guardar(e);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}
