package ProyectoCartas.controller;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public List<Boleta> listarBoletas() {return boletaService.listarBoletas();}

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarBoleta(@PathVariable Integer id){
        return boletaService.buscarBoletaPorId(id);
    }
}
