package ProyectoCartas.controller;


import ProyectoCartas.model.Carta;
import ProyectoCartas.service.CartaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping ("/api/v1/cartas")
public class CartaController {
    private final CartaService service;
    public CartaController (CartaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Carta> crear(@RequestBody Carta nuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevo));
    }
    @GetMapping
    public List<Carta> listar(){ return service.listar(); }
    @GetMapping("/{id}")
    public Carta obtener(@PathVariable Integer id){ return service.obtener(id); }
    @PutMapping("/{id}")
    public Carta actualizar(@PathVariable Integer id,@RequestBody Carta datos){
        Carta c = service.obtener(id);
        return service.guardar(c);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}
