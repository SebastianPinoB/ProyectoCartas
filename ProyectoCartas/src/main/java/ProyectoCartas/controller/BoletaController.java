package ProyectoCartas.controller;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boletas")
@Tag(name = "Boletas", description = "Generación de boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    @Operation(summary = "Obtener las boletas generadas", description = "Obtener todas las boletas generadas")
    public List<Boleta> listarBoletas() {return boletaService.listarBoletas();}

    @GetMapping("/{id}")
    @Operation(summary = "Buscar boleta por ID")
    public ResponseEntity<?> buscarBoleta(@PathVariable Integer id){
        return boletaService.buscarBoletaPorId(id);
    }
}
